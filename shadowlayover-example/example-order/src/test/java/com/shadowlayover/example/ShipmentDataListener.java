package com.shadowlayover.example;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/11/17-17:38
 * @desc:
 * </pre>
 */
@AllArgsConstructor
public class ShipmentDataListener implements ReadListener<ExampleOrderApplicationTest.ShipmentData> {


    private List<ExampleOrderApplicationTest.ShipmentData> cachedDataList;

    @Override
    public void onException(Exception e, AnalysisContext analysisContext) throws Exception {

    }

    @Override
    public void invokeHead(Map<Integer, CellData> map, AnalysisContext analysisContext) {

    }

    @Override
    public void invoke(ExampleOrderApplicationTest.ShipmentData shipmentData, AnalysisContext analysisContext) {
        cachedDataList.add(shipmentData);
    }

    @Override
    public void extra(CellExtra cellExtra, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    @Override
    public boolean hasNext(AnalysisContext analysisContext) {
        return false;
    }
}
