package vo;

import util.BillStatus;
import util.SalesOutBillType;
import util.Time;

import java.util.ArrayList;

/**
 * Description:
 * Created by Hanxinhu at 19:58 2017/11/21/021
 */
public class SalesOutBillVO {
    /**
     * 单据编号
     * 销售单为（XSD-yyyyMMdd-xxxxx）
     * 销售退货单位（XSTHD-yyyyMMdd-xxxxx）
     */
    public String ID;

    /**
     * 该单据的类型 是销售单还是销售退货单
     */
    public SalesOutBillType type;
    /**
     * 单据的状态
     */
    public BillStatus status;
    /**
     * 客户
     */
    public CustomerVO customerVO;
    /**
     * 业务员
     */
    public String DAE;

    /**
     * 仓库
     */
    public String storage;

    /**
     * 出库商品列表accountpo
     */
    public ArrayList<SalesItemVO> itemVOS;
    /**
     * 赠品列表
     */
    public ArrayList<PresentationCommodityItemVO> presentations;

    /**
     * 折让
     */
    public double allowance;
    /**
     * 使用的代金券金额
     */
    public int voucher;

    //fixme
    /**
     * 该订单赠送客户代价券的额度
     */
    public int presentation_voucher;
    /**
     * 创建订单时间
     */
    public Time init_time;
    /**
     * 提交时间
     */
    public Time commit_time;
    /**
     * 审批时间
     */
    public Time approval_time;
    /**
     * 审批的经理
     */
    public UserVO manager;


    /**
     * 生成该订单的销售人员
     */
    public UserVO operator;
    /**
     * 折让前总额
     */
    public double sumBeforeDiscount;
    /**
     *折让后总额
     */
    public double sumAfterDiscount;
    /**
     * 销售人员的备注
     */
    public String ps;

    public SalesOutBillVO(String ID, SalesOutBillType type, BillStatus status) {
        this.ID = ID;
        this.type = type;
        this.status = status;
        presentations = new ArrayList<>();
    }

    public SalesOutBillVO() {
    }

    public SalesOutBillVO(String ID, String DAE, String storage, SalesOutBillType type, double allowance, int voucher, CustomerVO customerVO, UserVO operator, UserVO manager
            , ArrayList<SalesItemVO> salesItemVOS, Time init_time, Time commit_time, Time approval_time, BillStatus status) {
        this.ID = ID;
        this.DAE = DAE;
        this.storage = storage;
        this.type = type;
        this.allowance = allowance;
        this.voucher = voucher;
        this.init_time = init_time;
        this.commit_time = commit_time;
        this.approval_time = approval_time;
        this.itemVOS = salesItemVOS;
        this.customerVO = customerVO;
        this.status = status;
        this.operator = operator;
        this.manager = manager;
    }

    public SalesOutBillVO(String ID, SalesOutBillType type, BillStatus status, CustomerVO customerVO, String DAE, String storage, ArrayList<SalesItemVO> itemVOS, ArrayList<PresentationCommodityItemVO> presentations, double allowance, int voucher, int presentation_voucher, Time init_time, Time commit_time, Time approval_time, UserVO manager, UserVO operator, double sumBeforeDiscount, double sumAfterDiscount) {
        this.ID = ID;
        this.type = type;
        this.status = status;
        this.customerVO = customerVO;
        this.DAE = DAE;
        this.storage = storage;
        this.itemVOS = itemVOS;
        this.presentations = presentations;
        this.allowance = allowance;
        this.voucher = voucher;
        this.presentation_voucher = presentation_voucher;
        this.init_time = init_time;
        this.commit_time = commit_time;
        this.approval_time = approval_time;
        this.manager = manager;
        this.operator = operator;
        this.sumBeforeDiscount = sumBeforeDiscount;
        this.sumAfterDiscount = sumAfterDiscount;
    }

    public SalesOutBillVO(String ID, SalesOutBillType type, BillStatus status, CustomerVO customerVO, String DAE, String storage, ArrayList<SalesItemVO> itemVOS, ArrayList<PresentationCommodityItemVO> presentations, double allowance, int voucher, int presentation_voucher, Time init_time, Time commit_time, Time approval_time, UserVO manager, UserVO operator, double sumBeforeDiscount, double sumAfterDiscount, String ps) {
        this.ID = ID;
        this.type = type;
        this.status = status;
        this.customerVO = customerVO;
        this.DAE = DAE;
        this.storage = storage;
        this.itemVOS = itemVOS;
        this.presentations = presentations;
        this.allowance = allowance;
        this.voucher = voucher;
        this.presentation_voucher = presentation_voucher;
        this.init_time = init_time;
        this.commit_time = commit_time;
        this.approval_time = approval_time;
        this.manager = manager;
        this.operator = operator;
        this.sumBeforeDiscount = sumBeforeDiscount;
        this.sumAfterDiscount = sumAfterDiscount;
        this.ps = ps;
    }

    public ArrayList<PresentationCommodityItemVO> getPresentations() {
        return presentations;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public void setPresentations(ArrayList<PresentationCommodityItemVO> presentations) {
        this.presentations = presentations;
    }

    public int getPresentation_voucher() {
        return presentation_voucher;
    }

    public void setPresentation_voucher(int presentation_voucher) {
        this.presentation_voucher = presentation_voucher;
    }

    public void setApproval_time(Time approval_time) {
        this.approval_time = approval_time;
    }

    public void setStatus(BillStatus status) {
        this.status = status;
    }

    public BillStatus getStatus() {
        return status;
    }

    public Time getApproval_time() {
        return approval_time;
    }

    public void setType(SalesOutBillType type) {
        this.type = type;
    }

    public Time getCommit_time() {
        return commit_time;
    }

    public void setCommit_time(Time commit_time) {
        this.commit_time = commit_time;
    }

    public Time getInit_time() {
        return init_time;
    }

    public void setInit_time(Time init_time) {
        this.init_time = init_time;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDAE() {
        return DAE;
    }

    public void setDAE(String DAE) {
        this.DAE = DAE;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public UserVO getManager() {
        return manager;
    }

    public void setItemVOS(ArrayList<SalesItemVO> itemVOS) {
        this.itemVOS = itemVOS;
    }

    public ArrayList<SalesItemVO> getItemVOS() {
        return itemVOS;
    }

    public void setManager(UserVO manager) {
        this.manager = manager;
    }

    public UserVO getOperator() {
        return operator;
    }

    public CustomerVO getCustomerVO() {
        return customerVO;
    }

    public void setOperator(UserVO operator) {
        this.operator = operator;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getSumAfterDiscount() {
        return sumAfterDiscount;
    }

    public void setCustomerVO(CustomerVO customerVO) {
        this.customerVO = customerVO;
    }

    public double getAllowance() {
        return allowance;
    }

    public int getVoucher() {
        return voucher;
    }

    public void setSumAfterDiscount(double sumAfterDiscount) {
        this.sumAfterDiscount = sumAfterDiscount;
    }

    public double getSumBeforeDiscount() {
        return sumBeforeDiscount;
    }

    public void setSumBeforeDiscount(double sumBeforeDiscount) {
        this.sumBeforeDiscount = sumBeforeDiscount;
    }

    public void setVoucher(int voucher) {
        this.voucher = voucher;
    }

    public SalesOutBillType getType() {
        return type;
    }
}
