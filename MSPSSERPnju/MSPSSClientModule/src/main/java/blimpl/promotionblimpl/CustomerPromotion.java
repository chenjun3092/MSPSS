package blimpl.promotionblimpl;

import network.PromotionClientNetworkService;
import po.CustomerPromotionPO;
import po.PresentationCommodityItemPO;
import util.*;
import vo.CustomerPromotionVO;
import vo.PresentationCommodityItemVO;

import java.util.ArrayList;

/**
 * Description:
 * Created by Hanxinhu at 10:05 2017/12/13/013
 */
public class CustomerPromotion {
    private PromotionClientNetworkService networkService ;
    /**
     * 得到所有当前时间可用的客户促销策略
     *
     * @return
     */

    public ArrayList<CustomerPromotionVO> getAvailableCustomerPromotion() {
        ArrayList<CriteriaClause> criteriaClauses = new ArrayList<>();
        Time time = new Time();
        CriteriaClause criteriaClause1 = CriteriaClauseImpl.createRangeValueQuery("init_time",Time.MIN_TIME,time.toString(), QueryMethod.Range);
        CriteriaClause criteriaClause2 = CriteriaClauseImpl.createRangeValueQuery("end_time",time.toString(),Time.MAX_TIME,QueryMethod.Range);
        criteriaClauses.add(criteriaClause1);
        criteriaClauses.add(criteriaClause2);
        ArrayList<CustomerPromotionPO> pos = networkService.multiSearchCustomerPromotion(criteriaClauses);
        return pos_to_vos(pos);
    }
    /**
     * 增加针对客户的促销策略
     *
     * @param promotionVO
     * @return
     */
    public ResultMessage addCustomerPromotion(CustomerPromotionVO promotionVO) {
        CustomerPromotionPO po = vo_to_po(promotionVO);
        return networkService.addCustomerPromotion(po);
    }

    /**
     * 更改针对客户的促销策略
     *
     * @param promotionVO
     * @return
     */

    public ResultMessage updateCustomerPromotion(CustomerPromotionVO promotionVO) {
        CustomerPromotionPO po = vo_to_po(promotionVO);
        return networkService.updateCustomerPromotion(po);
    }

    /**
     * 删除客户的促销策略
     *
     * @param promotionVO
     * @return
     */

    public ResultMessage deleteCustomerPromotion(CustomerPromotionVO promotionVO) {

        return networkService.deleteCustomerPromotion(promotionVO.getId());
    }

    /**
     * 得到所有的客户促销策略
     *
     * @return
     */

    public ArrayList<CustomerPromotionVO> getAllCustomerPromotion() {
        ArrayList<CriteriaClause> criteriaClauses = new ArrayList<>();
        criteriaClauses.add(CriteriaClauseImpl.createRangeValueQuery("init_time",Time.MIN_TIME,Time.MAX_TIME,QueryMethod.Range));
        ArrayList<CustomerPromotionPO> pos = networkService.multiSearchCustomerPromotion(criteriaClauses);

        return pos_to_vos(pos);
    }

    private CustomerPromotionPO vo_to_po(CustomerPromotionVO vo){
        ArrayList<PresentationCommodityItemPO> itemPOS = new ArrayList<>();
        for (PresentationCommodityItemVO presentationCommodityItemVO
                : vo.getPresentationCommodityItemVOS()) {
            itemPOS.add(presentationCommodityItemVO.to_po());
        }
     return new CustomerPromotionPO(vo.getLevel(),vo.getDiscount(),vo.getVoucher(),itemPOS,vo.getInitTime().toString(),vo.getEndTime().toString());
    }
    private CustomerPromotionVO po_to_vo(CustomerPromotionPO po){
        ArrayList<PresentationCommodityItemVO> itemVOS = new ArrayList<>();
        for (PresentationCommodityItemPO presentationCommodityItemPO
                : po.getPresentationCommodityItemPOS()) {
            itemVOS.add(new PresentationCommodityItemVO(presentationCommodityItemPO.getCommodityID()
            ,presentationCommodityItemPO.getNumber()));
        }
        return new CustomerPromotionVO(po.getId(),po.getLevel(),po.getDiscount(),po.getVoucher(),itemVOS,new Time(po.getInitTime()),
        new Time(po.getEndTime()));
    }

    /**
     * @param pos
     * @return
     */
    private ArrayList<CustomerPromotionVO> pos_to_vos(ArrayList<CustomerPromotionPO> pos){
        if (pos==null)
            return new ArrayList<>();
        ArrayList<CustomerPromotionVO> vos = new ArrayList<>();
        for(CustomerPromotionPO po : pos){
            vos.add(po_to_vo(po));
        }
        return vos;
    }


}