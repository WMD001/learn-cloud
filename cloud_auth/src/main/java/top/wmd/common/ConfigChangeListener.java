package top.wmd.common;

import com.alibaba.cloud.nacos.annotation.NacosConfigListener;
import org.springframework.stereotype.Component;

@Component
public class ConfigChangeListener {

    /**
     * dataId 和 group 必传,key 可空,不填时默认读取配置中的所有内容 @NacosConfigListener默认不会进行回调初始值，如果需要收到初始值，可以通过指定initNotify=true
     *
     * @param changedData 变更后的配置数据
     */
    @NacosConfigListener(dataId = "cloud_auth", group = "DEFAULT_GROUP", key = "dev.key")
    public void onChange(String changedData) {
        System.out.println("cloud.auth changed, new value: " + changedData);
    }

}
