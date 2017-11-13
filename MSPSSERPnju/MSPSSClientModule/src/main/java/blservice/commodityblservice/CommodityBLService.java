package blservice.commodityblservice;

import util.ResultMessage;
import vo.ClassificationVO;
import vo.CommodityVO;
import vo.FilterFlagVO;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Project_Name ERPnju
 * Author: HanXinHu
 * Description:
 * Created on 14:18 2017/11/11/011
 */
public interface CommodityBLService {
    /**
     *  添加新的商品
     * @param cvo
     * @return
     */
    public ResultMessage addCommodity(CommodityVO cvo);

    /**
     * 更新商品信息
     * @param cvo
     * @return
     */
    public ResultMessage updateCommodity(CommodityVO cvo);

    /**
     * 删除指定的商品
     * @param id 商品编号
     * @return
     */
    public ResultMessage deleteCommodity(String id);

    /**
     * 搜索商品
     * @param flags
     * @return
     */
    public Iterator<CommodityVO> searchCommodity(FilterFlagVO flags);

    /**
     * 添加商品分类信息
     * @param cvo
     * @return
     */
    public ResultMessage addClassification(ClassificationVO cvo);

    /**
     * 更新商品信息
     * @param cvo
     * @return
     */
    public ResultMessage updateClassification(ClassificationVO cvo);

    /**
     * 删除商品信息
     *
     * @param id@return
     */
    public ResultMessage deleteClassification(String id);

    /**
     * 获得商品分类
     * @param id
     * @return
     */
    public ClassificationVO getClassification(String id);

    /**
     * 得到所有的商品分类
     * @return
     */
    public ArrayList<ClassificationVO> getAllClassification();


}