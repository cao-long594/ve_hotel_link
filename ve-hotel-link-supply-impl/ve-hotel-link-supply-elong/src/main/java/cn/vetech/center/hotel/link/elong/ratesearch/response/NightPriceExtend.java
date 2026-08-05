package cn.vetech.center.hotel.link.elong.ratesearch.response;

/**
 * @author xiaotengyu
 * @since 2021/10/18 18:42
 */
public class NightPriceExtend {

    /**
     * 表示本RatePlan是否为限价产品 ，限价产品必须按照艺龙给出的售价进行售卖，即按照TotalRate指定的价格卖给客人
     * false --- 非限价
     * true --- 限价
     */
    private String date;
    /**
     *会员价 已经通过DRR的计算可以直接显示给客人。价格为-1表示不能销售。
     */
    private String member;
    /**
     * 结算价 仅用于结算价模式下的预付产品可用，非结算价模式下返回-1
     */
    private String cost;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}
