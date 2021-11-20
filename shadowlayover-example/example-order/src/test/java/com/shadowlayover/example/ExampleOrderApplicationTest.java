package com.shadowlayover.example;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import com.shadowlayover.common.db.generator.CodeGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/17-13:36
 * @desc:
 * </pre>
 */
@SpringBootTest
public class ExampleOrderApplicationTest {


    @Test
   public void test1() {

        List<ShipmentData> payLogDataList = Lists.newArrayList();
        File file =  new File("C:\\Users\\86136\\Desktop\\1117\\paylog.xlsx");

        EasyExcel.read("C:\\Users\\86136\\Desktop\\1117\\paylog.xlsx", ShipmentData.class, new ShipmentDataListener(payLogDataList)).sheet("export_result - 2021-11-17T1722").doRead();
        for (ShipmentData shipmentData  : payLogDataList) {
            System.out.println(shipmentData.getOrderNo());
        }

   }

   @Data
   public static class ShipmentData implements Serializable {

        private String orderNo;

        private BigDecimal price1;

        private BigDecimal price2;
   }
}
