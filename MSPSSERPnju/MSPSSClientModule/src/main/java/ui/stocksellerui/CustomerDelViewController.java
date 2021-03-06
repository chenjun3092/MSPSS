package ui.stocksellerui;

import auxiliary.Customer;
import blimpl.blfactory.BLFactoryImpl;
import blservice.customerblservice.CustomerBLService;
import blservice.mainblservice.MainBLService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainApp;
import main.StageSingleton;
import status.Log_In_Out_Status;
import ui.adminui.LoginController;
import ui.common.Dialog;
import util.ResultMessage;
import vo.CustomerVO;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * author:Jiang_Chen date:2017/12/13
 */
public class CustomerDelViewController implements Initializable {
    Stage stage = StageSingleton.getStage();
    CustomerBLService customerBLService = new BLFactoryImpl().getCustomerBLService();
    Dialog dialog = new Dialog();
    @FXML
    Button addCustomerButton;
    @FXML
    Button modCustomerButton;
    @FXML
    Button searchCustomerButton;
    @FXML
    Button cancelButtonl;
    @FXML
    Button BackToLogin, sureButton;
    @FXML
    TextField idField;

    public void sureButtonAction(ActionEvent e) {
        if (idField.getText() != null || !idField.getText().trim().equals("")) {
            if (customerBLService.getCustomerInfo(idField.getText()) != null) {
                boolean b = true;
                CustomerVO customerVO = customerBLService.getCustomerInfo(idField.getText());
                if (customerVO.getIncomemoney() != 0 || customerVO.getPaymoney() != 0) {
                    b = false;
                    dialog.errorInfoDialog("该客户的应收为" + customerVO.getIncomemoney() + '\n' + "该客户的应付为" + customerVO.getPaymoney() + '\n' + "该用户不可被删除");
                }
                if (b == true) {
                    ResultMessage resultMessage = customerBLService.delCustomer(idField.getText().trim());
                    if (resultMessage == ResultMessage.SUCCESS) {
                        dialog.infoDialog("Delete a customer successfully.");
                        try {
                            CustomerInfoShowViewController controller = (CustomerInfoShowViewController) replaceSceneContent(
                                    "/view/stockseller/CustomerInfoShow.fxml");
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    } else {
                        dialog.infoDialog("Fail to delete the customer");
                    }
                }
            } else {
                dialog.errorInfoDialog("Customer not found, id is wrong.");
            }
        } else {
            dialog.errorInfoDialog("You haven't input id.");
        }

    }

    /**
     * 返回登录界面
     *
     * @param e
     * @throws IOException
     */
    public void handleBackToLoginButtonAction(ActionEvent e) throws IOException {
        try {
            MainBLService mainBLService = new BLFactoryImpl().getMainBLService();
            boolean b = dialog.confirmDialog("Do you want to logout?");
            if (b == true) {
                LoginController controller = (LoginController) replaceSceneContent("/view/admin/Login.fxml");
                Log_In_Out_Status log_in_out_status = mainBLService.logout(idOfCurrentUser.getText());
                if (Log_In_Out_Status.Logout_Sucess == log_in_out_status) {
                    dialog.infoDialog("Logout successfully");
                }
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 返回上一界面
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void cancelButtonAction(ActionEvent e) throws IOException {
        try {
            CustomerManageViewController controller = (CustomerManageViewController) replaceSceneContent(
                    "/view/stockseller/CustomerManage.fxml");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 增加客户
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void customerAddButtonAction(ActionEvent e) throws IOException {
        try {
            CustomerAddViewController controller = (CustomerAddViewController) replaceSceneContent(
                    "/view/stockseller/CustomerAdd.fxml");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 修改客户属性
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void customerModifyButtonAction(ActionEvent e) throws IOException {
        try {
            CustomerModifyFirstViewController controller = (CustomerModifyFirstViewController) replaceSceneContent(
                    "/view/stockseller/CustomerModifyFirst.fxml");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 查找客户的结果显示
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void customerSearchButtonAction(ActionEvent e) throws IOException {

        try {
            CustomerSearchViewController controller = (CustomerSearchViewController) replaceSceneContent(
                    "/view/stockseller/CustomerSearch.fxml");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }


    /**
     * 用来打开fxml文件
     *
     * @param fxml
     * @return
     * @throws Exception
     */
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        // InputStream in =
        // Thread.currentThread().getContextClassLoader().getResourceAsStream(fxml);
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        Pane page;
        try {
            page = (Pane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 900, 560);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.setResizable(false);
        return (Initializable) loader.getController();
    }

    @FXML
    Label idOfCurrentUser, nameOfCurrentUser, categoryOfCurrentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idOfCurrentUser.setText("编号：" + LoginController.getCurrentUser().getID());
        nameOfCurrentUser.setText("姓名：" + LoginController.getCurrentUser().getName());
        categoryOfCurrentUser.setText("身份：" + LoginController.getCategory());
    }

}