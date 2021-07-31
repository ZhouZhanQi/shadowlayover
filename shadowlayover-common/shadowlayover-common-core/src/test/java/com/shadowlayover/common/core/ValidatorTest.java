package com.shadowlayover.common.core;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.base.Joiner;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <pre>
 * @author: zhouzhanqi
 * @datetime: 2021/7/20-21:38
 * @desc: 参数校验工具类
 * </pre>
 */
public class ValidatorTest {
    
    
    public static void main(String[] args) {
    
        String fileName = "C:\\Users\\86136\\Desktop\\电商见证宝接口(OpenApi)V1.43\\电商见证宝接口(OpenApi)V1.43\\接口文档OpenApi(非自营)\\平安银行能力开放平台电商见证宝接口文档-不验证（非自营标准）V1.4.3-hwx.xlsx";
    
        String ejzbBankFileName = "C:\\Users\\86136\\Desktop\\文档\\ejzb_bank.xlsx";
        
        List<BankInfo> dataBank = Lists.newArrayList();
    
        EasyExcel.read(fileName, BankInfo.class, new BankInfoDataListener(dataBank))
                .sheet("超级网银号").doRead();
    
    
        List<BankInfoSupport> dataSupportBank = Lists.newArrayList();
        EasyExcel.read(fileName, BankInfoSupport.class, new BankInfoSupportDataListener(dataSupportBank))
                .sheet("支持银联验证的银行列表").doRead();
    
    
        int size = dataSupportBank.stream().map(BankInfoSupport::getAccessLineNumber).distinct().collect(Collectors.toList()).size();
        System.out.println("支持的银行数量: " + size);
        
        
        
    
        List<EjzbBankInfo> ejzbBankInfoList = Lists.newArrayList();
        EasyExcel.read(ejzbBankFileName, EjzbBankInfo.class, new EjzbBankInfoListener(ejzbBankInfoList))
                .sheet("ejzb_bank").doRead();
    
        ejzbBankInfoList.stream().filter(data -> {
            List<String> numList = dataSupportBank.stream().map(BankInfoSupport::getAccessLineNumber).distinct().collect(Collectors.toList());
            return !numList.contains(data.getBankSuperNo());
        }).forEach(bankInfo -> {
            System.out.println("存在支持银行以外的银行: " + bankInfo.getBankShortName());
        });
    
       
    
    
        dataSupportBank.stream().filter(data -> {
                    List<String> hasBankList = ejzbBankInfoList.stream().map(EjzbBankInfo::getBankSuperNo).collect(Collectors.toList());
                    return !hasBankList.contains(data.getAccessLineNumber());
                }).forEach(supportData -> {
                Optional<BankInfo> bankInfo = dataBank.stream().filter(bankData -> bankData.getSuperNo().equals(supportData.getAccessLineNumber())).findFirst();
                
                if (bankInfo.isPresent()) {
                    BankInfo bankInfoData = bankInfo.get();
                    String values = Joiner.on(",").join( "'admin'", 1, "now()", "'admin'", 1, "now()", 1, "''",
                            "'" + bankInfoData.getBankFullName() + "'", "'" + bankInfoData.getBankShortName() + "'", "'" + bankInfoData.getSuperNo() + "'", "'" + bankInfoData.getBankCode() + "'", "'" + bankInfoData.getLiquidationNo() + "'", "'1510'");
    
                    String insertValue = "INSERT INTO `trade`.`ejzb_bank` (`creator`, `creator_id`, `create_time`, `updater`, `updater_id`, `update_time`, `enabled_flag`, `trace_id`, `bank_full_name`, `bank_short_name`, `bank_super_no`, `bank_cls_code`, `bank_drec_code`, `bank_logo_code`) VALUES";
                    System.out.println(insertValue + " (" + values + ");");
                    
                } else {
                    String values = Joiner.on(",").join( "'admin'", 1, "now()", "'admin'", 1, "now()", 1, "''",
                            "'" + supportData.getBankFullName() + "'", "'" + supportData.getBankShortName() + "'", "'" + supportData.getAccessLineNumber() + "'", "'1000'", "'" + supportData.getAccessLineNumber() + "'", "'1510'");
    
                    String insertValue = "INSERT INTO `trade`.`ejzb_bank` (`creator`, `creator_id`, `create_time`, `updater`, `updater_id`, `update_time`, `enabled_flag`, `trace_id`, `bank_full_name`, `bank_short_name`, `bank_super_no`, `bank_cls_code`, `bank_drec_code`, `bank_logo_code`) VALUES";
                    System.out.println(insertValue + " (" + values + ");");
                }
        });
        
        
    }
    
    @Data
    public static class BankInfo {
        
        private String superNo;
        
        private String bankFullName;
        
        private String bankShortName;
        
        private String bankCode;
        
        private String liquidationNo;
    }
    
    @Data
    public static class BankInfoSupport {
        private Long orderNum;
    
        private String bankShortName;
    
        private String bankFullName;
        
        private String accessLineNumber;
    }
    
    @Data
    public static class EjzbBankInfo {
    
        private String bankFullName;
    
        private String bankShortName;
        
        private String bankSuperNo;
        
        private String bankClsCode;
        
        private String bankDrecCode;
        
        private String bankLogoCode;
    }
    
    
    
    public static class BankInfoDataListener extends AnalysisEventListener<BankInfo> {
    
    
        List<BankInfo> list;
    
        public BankInfoDataListener(List<BankInfo> list) {
            this.list = list;
        }
    
        @Override
        public void invoke(BankInfo bankInfo, AnalysisContext analysisContext) {
            list.add(bankInfo);
            // System.out.println(JSONUtil.toJsonStr(bankInfo));
        }
    
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        
        }
        
        private void saveData() {
        
        }
    }
    
    
    public static class BankInfoSupportDataListener extends AnalysisEventListener<BankInfoSupport> {
        
        
        List<BankInfoSupport> list;
        
        public BankInfoSupportDataListener(List<BankInfoSupport> list) {
            this.list = list;
        }
        
        @Override
        public void invoke(BankInfoSupport bankInfo, AnalysisContext analysisContext) {
            list.add(bankInfo);
            // System.out.println(JSONUtil.toJsonStr(bankInfo));
        }
        
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        
        }
        
        private void saveData() {
        
        }
    }
    
    public static class EjzbBankInfoListener extends AnalysisEventListener<EjzbBankInfo> {
        
        
        List<EjzbBankInfo> list;
        
        public EjzbBankInfoListener(List<EjzbBankInfo> list) {
            this.list = list;
        }
        
        @Override
        public void invoke(EjzbBankInfo bankInfo, AnalysisContext analysisContext) {
            list.add(bankInfo);
            // System.out.println(JSONUtil.toJsonStr(bankInfo));
        }
        
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        
        }
        
        private void saveData() {
        
        }
    }
    
}
