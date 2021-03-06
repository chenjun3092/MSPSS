package blimpl.billblimpl;

import blimpl.blfactory.BLFactoryImpl;
import blservice.accountblservice.AccountBLInfo;
import blservice.userblservice.UserInfo;
import network.BillClientNetworkImpl;
import network.BillClientNetworkService;
import po.CashCostBillPO;
import po.CashCostItemPO;
import util.BillStatus;
import util.ResultMessage;
import util.Time;
import vo.AccountVO;
import vo.CashCostBillVO;
import vo.CashCostItemVO;
import vo.UserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by Hanxinhu at 11:33 2017/12/19/019
 */
public class CashCostBill {
    private BillClientNetworkService networkService = new BillClientNetworkImpl();
    private AccountBLInfo accountBLInfo = new BLFactoryImpl().getAccountBLInfo();
    private UserInfo userInfo = new BLFactoryImpl().getUserInfo();
    /**
     * 保存现金费用单
     *
     * @param cashCostBillVO
     * @return
     */
    public ResultMessage saveCashCostBill(CashCostBillVO cashCostBillVO) {
        //save
        //String id = vo.getID();
        cashCostBillVO.setStatus(BillStatus.init);
        if (cashCostBillVO.getInit_time() == null) {
            cashCostBillVO.setInit_time(new Time());
        }
        if (cashCostBillVO.getID() == null) {
            String ID = networkService.getCashCostBillID();
            cashCostBillVO.setID(ID);
            if (userInfo.getCurrentUser() != null) {
                BillLogHelper.init(userInfo.getCurrentUser(), ID);
            }
            return networkService.addCashCostBill(vo_to_po(cashCostBillVO));
        } else {
            if (userInfo.getCurrentUser() != null) {
                BillLogHelper.update(userInfo.getCurrentUser(), cashCostBillVO.getID());

            }
            return networkService.updateCashCostBill(vo_to_po(cashCostBillVO));
        }
    }



    /**
     * 提交现金费用单
     *
     * @param cashCostBillVO
     * @return
     */
    public ResultMessage commitCashCostBill(CashCostBillVO cashCostBillVO) {
        cashCostBillVO.setStatus(BillStatus.commit);
        cashCostBillVO.setCommit_time(new Time());
        if (userInfo.getCurrentUser() != null) {
            BillLogHelper.commit(userInfo.getCurrentUser(), cashCostBillVO.getID());
            BillSendMessage.commit(userInfo.getCurrentUser(), cashCostBillVO.getID());
        }
        return networkService.updateCashCostBill(vo_to_po(cashCostBillVO));
    }

    ;

    /**
     * 删除现金费用单
     *
     * @param cashCostBillVO
     * @return
     */
    public ResultMessage deleteCashCostBill(CashCostBillVO cashCostBillVO) {
        BillLogHelper.delete(userInfo.getCurrentUser(), cashCostBillVO.getID());

        return networkService.deleteCashCostBill(cashCostBillVO.getID());
    }



    /**
     * 得到某操作员创建的所有现金费用单
     *
     * @param operatorID
     * @return
     */
    public ArrayList<CashCostBillVO> getMyCashCostBill(String operatorID) {
        ArrayList<CashCostBillPO> pos = networkService.fullSearchCashCostBill("operatorID", operatorID);
        return pos_to_vos(pos);
    }

    ;

    /**
     * 撤回已经提交的现金费用单
     *
     * @param cashCostBillVO
     * @return
     */
    public ResultMessage withdrawCashCostBill(CashCostBillVO cashCostBillVO) {
        cashCostBillVO.setStatus(BillStatus.init);
        cashCostBillVO.setCommit_time(null);
        if (userInfo.getCurrentUser() != null) {
            BillLogHelper.withdraw(userInfo.getCurrentUser(), cashCostBillVO.getID());
            BillSendMessage.withdraw(userInfo.getCurrentUser(), cashCostBillVO.getID());
        }
        return networkService.updateCashCostBill(vo_to_po(cashCostBillVO));
    }

    /**
     * 得到待审批的现金费用单
     *
     * @return
     */
    public ArrayList<CashCostBillVO> getWaitingCashCostBill() {
        ArrayList<CashCostBillPO> pos = networkService.fullSearchCashCostBill("status", BillStatus.commit.ordinal());
        System.out.print(pos==null);
        return pos_to_vos(pos);
    }

    /**
     * 通过单据
     *
     * @param vo
     * @return
     */
    public ResultMessage approveCashCostBill(CashCostBillVO vo) {
        vo.setApproval_time(new Time());
        vo.setStatus(BillStatus.approval);
        //完成账户余额的改变

        accountBLInfo.pay(vo.accountVO.getName(), vo.getSum());
        if (userInfo.getCurrentUser() != null) {
            BillLogHelper.approval(userInfo.getCurrentUser(), vo.getID());
            BillSendMessage.approve(vo.getOperator(), vo.getManager(), vo.getID());
        }
        return networkService.updateCashCostBill(vo_to_po(vo));
    }

    public ResultMessage rejectCashCostBill(CashCostBillVO vo) {
        vo.setApproval_time(new Time());
        vo.setStatus(BillStatus.rejected);
        if (userInfo.getCurrentUser() != null) {
            BillSendMessage.reject(vo.getOperator(), vo.getManager(), vo.getID());
            BillLogHelper.reject(vo.manager, vo.getID());
        }
        return networkService.updateCashCostBill(vo_to_po(vo));
    }

    private CashCostBillPO vo_to_po(CashCostBillVO vo) {
        ArrayList<CashCostItemPO> itemPOS = new ArrayList<>();
        for (int i = 0; i < vo.getList().size(); i++) {
            itemPOS.add(vo_to_po(vo.getList().get(i)));
        }
        //防止出现null报错
        String commitTime = vo.getCommit_time() != null ? vo.getCommit_time().toString() : null;
        String approvalTime = vo.getApproval_time() != null ? vo.getApproval_time().toString() : null;
        String managerID = vo.getManager() != null ? vo.getManager().getID() : null;


        CashCostBillPO po = new CashCostBillPO(vo.getID(), vo.getOperator().getID(), managerID, vo.getStatus().ordinal()
                , itemPOS, vo.sum, vo.getInit_time().toString(), commitTime, approvalTime, vo.accountVO.getName());

        return po;
    }

    public CashCostBillVO po_to_vo(CashCostBillPO po) {
        //谨慎 谨慎 再谨慎
        Time commitTime = po.getCommit_time() != null ? new Time(po.getCommit_time()) : null;
        Time approvalTime = po.getApproval_time() != null ? new Time(po.getApproval_time()) : null;
        UserVO managerVO = null;
        System.out.println("!"+po.getManagerID());
        if (po.getManagerID()!=null)
        if (!po.getManagerID().equals(""))
        managerVO = userInfo.getUser(po.getManagerID());

        UserVO operatorVO = userInfo.getUser(po.getOperatorID());
        BillStatus status = BillStatus.values()[po.getStatus()];
        AccountVO accountVO = accountBLInfo.getAccountVO(po.getAccountName());
        ArrayList<CashCostItemVO> itemVOS = new ArrayList<>();
        List<CashCostItemPO> list = po.getList();
        for (CashCostItemPO itemPO : list) {
            itemVOS.add(po_to_vo(itemPO));
        }


        CashCostBillVO vo = new CashCostBillVO(po.getID(), operatorVO, managerVO, status, accountVO, itemVOS, po.getSum(),
                new Time(po.getInit_time()), commitTime, approvalTime);
        return vo;
    }

    public ResultMessage HongChong(CashCostBillVO cashCostBillVO) {
        String ID = cashCostBillVO.getID() + "HC";
        //fixme 存疑 不知道是否应该在原单据编号后面直接加上HC还是重新生成一个编号
        cashCostBillVO.setSum(cashCostBillVO.getSum() * -1);
        cashCostBillVO.setID(ID);
        cashCostBillVO.setStatus(BillStatus.approval);
        cashCostBillVO.setInit_time(new Time());
        cashCostBillVO.setCommit_time(new Time());
        cashCostBillVO.setApproval_time(new Time());
        accountBLInfo.pay(cashCostBillVO.accountVO.getName(), cashCostBillVO.getSum());
        if (userInfo.getCurrentUser() != null) {
            BillLogHelper.approval(userInfo.getCurrentUser(), cashCostBillVO.getID());
            BillSendMessage.approve(cashCostBillVO.getOperator(), cashCostBillVO.getManager(), cashCostBillVO.getID());
        }
        return networkService.addCashCostBill(vo_to_po(cashCostBillVO));
    }

    public ResultMessage HongChongAndCopy(CashCostBillVO cashCostBillVO) {
        String ID = cashCostBillVO.getID() + "HCCopy";
        //fixme 存疑 不知道是否应该在原单据编号后面直接加上HC还是重新生成一个编号
        cashCostBillVO.setID(ID);
        cashCostBillVO.setStatus(BillStatus.approval);
        cashCostBillVO.setInit_time(new Time());
        cashCostBillVO.setCommit_time(new Time());
        cashCostBillVO.setApproval_time(new Time());
        accountBLInfo.pay(cashCostBillVO.accountVO.getName(), cashCostBillVO.getSum());
        if (userInfo.getCurrentUser() != null) {
            BillLogHelper.approval(userInfo.getCurrentUser(), cashCostBillVO.getID());
            BillSendMessage.approve(cashCostBillVO.getOperator(), cashCostBillVO.getManager(), cashCostBillVO.getID());
        }
        return networkService.addCashCostBill(vo_to_po(cashCostBillVO));
    }
    private CashCostItemPO vo_to_po(CashCostItemVO vo) {
        return new CashCostItemPO(vo.getName(), vo.getPs(), vo.getMoney());
    }

    private CashCostItemVO po_to_vo(CashCostItemPO po) {
        return new CashCostItemVO(po.getName(), po.getMoney(), po.getPs());
    }

    private ArrayList<CashCostBillVO> pos_to_vos(ArrayList<CashCostBillPO> pos) {
        ArrayList<CashCostBillVO> vos = new ArrayList<>();

        for (int i = 0; i < pos.size(); i++) {
            vos.add(po_to_vo(pos.get(i)));
        }
        return vos;
    }



}
