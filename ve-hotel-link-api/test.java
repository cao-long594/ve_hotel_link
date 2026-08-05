/**
 * 价格明细
 *
 * @author gaojin
 */
public class BookNightlyRate implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 当天日期
     */
    @ApiModelProperty(value = "当天日期", dataType = "string")
    private String date;
    /**
     * 税后价
     */
    @ApiModelProperty(value = "税后价", dataType = "string")
    private String priceAfterTax;
    /**
     * 税前价
     */
    @ApiModelProperty(value = "税前价", dataType = "string")
    private String priceBeforTax;
    /**
     * 税费
     */
    @ApiModelProperty(value = "税费", dataType = "string")
    private String tax;
    /**
     * 库存状态
     */
    @ApiModelProperty(value = "库存状态", dataType = "string")
    private String status;
