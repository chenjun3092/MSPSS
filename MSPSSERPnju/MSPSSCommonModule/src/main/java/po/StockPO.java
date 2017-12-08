package po;

import util.StockInfo;

import java.io.Serializable;

/**
 * @Project_Name ERPnju
 * @Author: HanXinHu
 * @Description:
 * @Date Created in 18:41 2017/11/6/006
 */
public class StockPO implements Serializable{
    /**
     * inOrOut 出库还是入库
     */
   private StockInfo inOrOut;
    /**
     * 商品编号
     */
    private  String commodityID;
    /**
     * 商品的库存变化数量
     */
    private int number;
    /**
     * 库存变动的时间
     */
    private String time;
    /**
     * 商品的出入库金额 入库是进价乘以数量 出库是售价乘以数量
     */
    private double price;

    public StockPO(StockInfo inOrOut, String commodityID, int number, String time, double price) {
        this.inOrOut = inOrOut;
        this.commodityID = commodityID;
        this.number = number;
        this.time = time;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(String commodityID) {
        this.commodityID = commodityID;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setInOrOut(StockInfo inOrOut) {
        this.inOrOut = inOrOut;
    }

    public StockInfo getInOrOut() {
        return inOrOut;
    }
}
