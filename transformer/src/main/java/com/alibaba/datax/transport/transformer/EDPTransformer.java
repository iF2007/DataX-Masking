package com.alibaba.datax.transport.transformer;

import com.alibaba.datax.common.element.*;
import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.transport.transformer.maskingMethods.differentialPrivacy.EpsilonDifferentialPrivacyImpl;
import com.alibaba.datax.transport.transformer.maskingMethods.irreversibleInterference.MD5EncryptionImpl;

import java.util.Arrays;

/**
 * Created by Liu Kun on 2018/5/9.
 *
 * 目前只实现了针对数值型数据提供中心化差分隐私支持的干扰技术——拉普拉斯机制
 *
 * 尚未实现针对类别型数据的指数机制，还有针对本地化差分隐私的随机应答干扰机制
 */
public class EDPTransformer extends Transformer{
    private Object masker;
    String key;
    int columnIndex;

    public EDPTransformer(){
        setTransformerName("dx_edp_lap");
        System.out.println("Using Epsilon Differential Privacy masker");
    }

    @Override
    public Record evaluate(Record record, Object... paras) {
        try {
            if (paras.length < 2) {
                throw new RuntimeException("dx_edp_lap transformer缺少参数");
            }
            columnIndex = (Integer) paras[0];
            key = (String) paras[1];
        } catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_ILLEGAL_PARAMETER, "paras:" + Arrays.asList(paras).toString() + " => " + e.getMessage());
        }
        Column column = record.getColumn(columnIndex);
        try {
            String oriValue = column.asString();
            if (oriValue == null) {
                return record;
            }
            if(column.getType() == Column.Type.DOUBLE) {
                double newValue;
                EpsilonDifferentialPrivacyImpl masker = new EpsilonDifferentialPrivacyImpl();
                newValue = masker.maskOne(Double.parseDouble(oriValue), Double.parseDouble(key));
                record.setColumn(columnIndex, new DoubleColumn(newValue));
            }
            else if(column.getType() == Column.Type.LONG){
                long newValue;
                EpsilonDifferentialPrivacyImpl masker = new EpsilonDifferentialPrivacyImpl();
                newValue = masker.maskOne(Long.valueOf(oriValue), Double.parseDouble(key));
                record.setColumn(columnIndex, new LongColumn(newValue));
            }
        } catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_RUN_EXCEPTION, e.getMessage(), e);
        }
        return record;
    }
}
