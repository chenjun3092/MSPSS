package network;

import po.StockBillPO;
import util.CriteriaClause;
import util.ResultMessage;
import util.StockBillType;

import java.util.ArrayList;

/**
 * Description:
 * Created by Hanxinhu at 14:06 2017/11/30/030
 */
public interface BillClientNetworkService {
    /**
     * 添加库存类单据 用于点击保存时
     * @param stockBillPO
     * @return
     */
    public ResultMessage addStockBill(StockBillPO  stockBillPO);

    /**
     * 更新库存类单据 用于点击提交时
     * @param stockBillPO
     * @return
     */
    public ResultMessage updateStockBill(StockBillPO stockBillPO);

    /**
     * 删除某单据 未提交状态
     *
     * @param id
     * @return
     */
    public ResultMessage deleteStockBill(String id);

    /**
     *用于查找某一数据
     */
    public ArrayList<StockBillPO> fullSearchStockBill(String field, Object value);


    public ArrayList<StockBillPO> multiSearchStockBill(ArrayList<CriteriaClause> criteriaClauses);

    /**
     * 返回形如 KDZSD-20101010-00001
     *
     * @param type
     * @return
     */
    public String getStockBillID(StockBillType type);
}
