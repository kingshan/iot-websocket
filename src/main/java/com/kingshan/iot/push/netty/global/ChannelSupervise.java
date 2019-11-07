package com.kingshan.iot.push.netty.global;

import com.kingshan.iot.push.exception.GroupNotFindException;
import com.kingshan.iot.push.exception.UserNotFindException;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 14:44
 */

@Slf4j
@Component
public class ChannelSupervise {

    //所有连接Channel
    private  static ChannelGroup GlobalGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //用户连接Channel
    private  static ConcurrentMap<String, ChannelId> userChannelMap=new ConcurrentHashMap();

    //群组连接Channel
    private  static ConcurrentMap<String, HashSet<ChannelId>> groupChannelMap=new ConcurrentHashMap();

    //ChannelId与用户对应关系
    private  static ConcurrentMap<ChannelId, String> channelIdUserMap =new ConcurrentHashMap();

    //ChannelId与群组对应关系
    private  static ConcurrentMap<ChannelId, String> channelIdGroupMap =new ConcurrentHashMap();

    /**
     * 新增连接Channel
     * @param userId
     * @param groupId
     * @param channel
     */
    public  static void addChannel(String userId, String groupId,Channel channel){
        GlobalGroup.add(channel);
        userChannelMap.put(userId,channel.id());
        if(groupChannelMap.containsKey(groupId)){
            HashSet<ChannelId> set = groupChannelMap.get(groupId);
            set.add(channel.id());
            groupChannelMap.put(groupId,set);
        }else{
            HashSet<ChannelId> set = new HashSet<ChannelId>();
            set.add(channel.id());
            groupChannelMap.put(groupId,set);
        }
        channelIdUserMap.put(channel.id(),userId);
        channelIdGroupMap.put(channel.id(),groupId);
    }


    /**
     * 移除连接Channel
     * @param channel
     */
    public static void removeChannel(Channel channel){
        userChannelMap.remove(channelIdUserMap.get(channel.id()));
        channelIdUserMap.remove(channel.id());
        groupChannelMap.get(channelIdGroupMap.get(channel.id())).remove(channel.id());
        if(0 == groupChannelMap.get(channelIdGroupMap.get(channel.id())).size()){
            groupChannelMap.remove(channelIdGroupMap.get(channel.id()));
        }
        channelIdGroupMap.remove(channel.id());
        GlobalGroup.remove(channel);

    }

    /**
     * 通过用户ID查找Channel
     * @param userId
     * @return
     */
    private static  Channel findUserChannel(String userId){
        if(!userChannelMap.containsKey(userId)){
            throw  new UserNotFindException("userId: " +userId + " not exist");
        }
        return GlobalGroup.find(userChannelMap.get(userId));
    }

    /**
     * 通过群组ID查找Channel  支持通配符*
     * @param groupId
     * @return
     */
    private static  List<Channel> findGroupChannel(String groupId){
        List<Channel> list=new Vector<>();
        if(groupId.endsWith("*")){
            groupId =  groupId.substring(0,groupId.length()-1);
            for (Map.Entry<String, HashSet<ChannelId>> entity : groupChannelMap.entrySet()) {
                if(entity.getKey().indexOf(groupId)>-1){
                    entity.getValue().stream().forEach(channelId -> {
                        list.add(GlobalGroup.find(channelId));
                    });
                }
            }
        }else{
            if(!groupChannelMap.containsKey(groupId)){
                throw  new GroupNotFindException("groupId: " +groupId +" not exist");
            }
            groupChannelMap.get(groupId).stream().forEach(channelId -> {
                list.add(GlobalGroup.find(channelId));
            });
        }
        return list;
    }

    /**
     * 向所有用户推送消息
     * @param tws
     */
    public static void send2All(String tws){
        GlobalGroup.writeAndFlush(new TextWebSocketFrame(tws));
    }

    /**
     * 向用户推送消息
     * @param userId
     * @param tws
     */
    public static void send2User(String userId, String tws){
        findUserChannel(userId).writeAndFlush(new TextWebSocketFrame(tws));
    }

    /**
     * 向群组推送消息
     * @param groupId
     * @param tws
     */
    public static void send2Group(String groupId, String tws){
        findGroupChannel(groupId).stream().forEach(channel -> {
            channel.writeAndFlush(new TextWebSocketFrame(tws));
        });
    }

    /**
     * 获得连接用户列表
     * @return
     */
    public static  List<String> getUserList(){
        List<String> list = new ArrayList<>();
        userChannelMap.entrySet().stream().forEach(stringChannelIdEntry -> {
            list.add(stringChannelIdEntry.getKey());
        });
        return list;
    }

    /**
     * 在线用户数量
     * @return
     */
    public static Integer  getUserCount() {
        return  userChannelMap.size();
    }

    /**
     * 获取群组列表
     * @return
     */
    public static  List<String> getGroupList(){
        List<String> list = new ArrayList<>();
        groupChannelMap.entrySet().stream().forEach(stringHashSetEntry -> {
            list.add(stringHashSetEntry.getKey());
        });
        return list;
    }

    /**
     * 获取群组用户列表
     * @param groupId
     * @return
     */
    public static List<String>  getGroupUserList(String groupId) {
        List<String> list = new ArrayList<>();
        if(!groupChannelMap.containsKey(groupId)){
            throw  new GroupNotFindException("groupId not exist");
        }
        groupChannelMap.get(groupId).stream().forEach(channelId -> {
            if(channelIdUserMap.containsKey(channelId)){
                list.add(channelIdUserMap.get(channelId));
            }
        });
        return  list;
    }

    /**
     * 获取群组数量
     * @return
     */
    public static Integer  getGroupCount() {
        return  groupChannelMap.size();
    }


    /**
     * 获取群组用户数量
     * @param groupId
     * @return
     */
    public static Integer  getGroupUserCount(String groupId) {
        if(!groupChannelMap.containsKey(groupId)){
            return  0;
        }
        return  groupChannelMap.get(groupId).size();
    }

    /**
     * 判断用户是否在线
     * @param userId
     * @return
     */
    public static boolean isOnline(String userId){
        if(userChannelMap.containsKey(userId)){
            return  true;
        }
        return  false;
    }

}
