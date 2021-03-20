package com.example.ltx.eshare.common.constant;

/**
 * @author Liu-PC
 * 在interface中定义的常量默认是public static final类型的
 * 就相当于默认加了public static final前缀
 */
public interface Constants {
     enum Dict{
        PROSTA("PROSTA","产品状态"),
        COUNTRY("COUNTRY","国家"),

        YWLX("YWLX","业务类型"),
        INDUSTRYCOMPANYTYPE("IndustryCompanyType","公司类型"),
        JSFS("JSFS","结算方式"),
        COMMISSIONTYPE("COMMISSIONTYPE","返佣类型"),
        BALUNITTYPE("BALUNITTYPE","结算单位类型"),
        ORDERSTATS("OrderStats","订单状态"),
        BACKORDERSTATUS("BackOrderStatus","退单审核状态"),
        BUSINESSPAYMENT("BusinessPayment","业务款项"),
        ENABLESTATE("enableState","启用禁用"),
        APPROVESTATE("approveState","审批状态"),
        //        分销系统所需是商品系统的xml
        PRODUCTCONTENTTYPE("productContentType","商品内容分类"),
        IDENTITY("identity","适应人群"),
        AREA("area","领区"),
        VISATYPE("visatype","签证类型"),
        SERVICETYPE("serviceType","公证认证商品内容分类"),
        PRODUCTTYPEQUALITY("productTypeQuality","公证认证商品性质"),
        EXPRESSTYPE("expresstype","公证认证加急种类"),
        IDETIFICATIONTYPE("identificationType","认证类别"),
        QYKHLX("QYKHLX","客户类型"),
        ZILIAONAME("ziliaoName","资料名称"),
        YESORNO("yesOrNo","是否");



        Dict(String value, String name){
            this.value=value;
            this.name=name;
        }
        private final String value;
        private final String name;

        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
    /**
     * 订单状态
     * <p>Company:rayootech</p>
     * @author zhangxueshen
     * @date 2016-6-14
     */
    public enum OrderStats{

        DELETE(0,"删除"),RESERVE(1,"订单预定"),CONFIRM(2,"订单确认"),COMPLETE(3,"订单完成"),CLOSE(4,"订单关闭");

        OrderStats(Integer value, String name){
            this.value = value;
            this.name = name;
        }
        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

    }

    public static void main(String[] args) {

    }
}
