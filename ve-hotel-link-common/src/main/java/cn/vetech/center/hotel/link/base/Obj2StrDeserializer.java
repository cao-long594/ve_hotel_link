package cn.vetech.center.hotel.link.base;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NullNode;

import java.io.IOException;
import java.util.Objects;

/**
 * @author chengwanshan
 * @since 2025/1/16 12:02
 */
public class Obj2StrDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = p.getCodec().readTree(p);
        if (Objects.isNull(treeNode) || treeNode instanceof NullNode) {
            return null;
        }
        return treeNode.toString();
    }
}
