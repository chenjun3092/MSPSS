package ui.stockmanagerui;

import blimpl.blfactory.BLFactoryImpl;
import blservice.commodityblservice.CommodityBLService;
import blservice.commodityblservice.CommodityInfoService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.MainApp;
import main.StageSingleton;
import ui.adminui.LoginController;
import ui.common.Dialog;
import vo.ClassificationVO;
import vo.CommodityVO;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * author:Jiang_Chen
 * date:2017/12/8
 */
public class CommodityModitySecondViewController implements Initializable {
    Stage stage = StageSingleton.getStage();
    Dialog dialog = new Dialog();
    CommodityBLService commodityBLService = new BLFactoryImpl().getCommodityBLService();
    CommodityInfoService commodityInfoService = new BLFactoryImpl().getCommodityInfoService();
    static String id_to_modify;

    @FXML
    Button BackToLogin;
    @FXML
    Button commodityDelButton;
    @FXML
    Button commodityAddButton;
    @FXML
    Button commoditySearchButton;
    @FXML
    Button backToBeforeButton;
    @FXML
    Button cancelButton, sureButton;
    @FXML
    TextField nameField, importPriceField, exportPriceField, typeField, alertField, stockNumberField;
    @FXML
    ComboBox<String> classificationBox;
    @FXML
    Label idLabel;

    public void showClassificationBox(ArrayList<ClassificationVO> list) {
        for (int i = 0; i < list.size(); i++) {
            if (commodityBLService.getChildrenClassification(list.get(i)).size() == 0) {
                classificationBox.getItems().add(list.get(i).getName());
            } else {
                ArrayList<ClassificationVO> list2 = commodityBLService.getChildrenClassification(list.get(i));
                showClassificationBox(list2);
            }
        }
    }

    /**
     * 修改按钮
     *
     * @param e
     */
    public void sureButtonAction(ActionEvent e) {
        if (id_to_modify != null || !id_to_modify.equals("")) {
            CommodityVO commodityVO = commodityInfoService.getCommodity(id_to_modify.trim());
            commodityBLService.deleteCommodity(id_to_modify.trim());
            CommodityVO newcommodity = new CommodityVO(nameField.getText().trim(), typeField.getText(), classificationBox.getValue(), Double.parseDouble(importPriceField.getText().trim()), Double.parseDouble((exportPriceField.getText().trim())));
            newcommodity.setAlertNumber(Integer.parseInt(alertField.getText().trim()));
            newcommodity.setID(commodityVO.getID());
            newcommodity.setLatestExportCost(commodityVO.getLatestExportCost());
            newcommodity.setLatestImportCost(commodityVO.getLatestImportCost());
            newcommodity.setNumberInStock(Integer.parseInt(stockNumberField.getText().trim()));
            commodityBLService.addCommodity(newcommodity);

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
            CommodityManageViewController controller = (CommodityManageViewController) replaceSceneContent(
                    "/view/stockmanager/commodityManage.fxml");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 返回用户管理界面
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void backToBeforeButtonAction(ActionEvent e) throws IOException {
        try {
            CommodityModifyFirstViewController controller = (CommodityModifyFirstViewController) replaceSceneContent(
                    "/view/stockmanager/commodityModifyFirst.fxml");
            controller.id_to_modify_Field.setText(id_to_modify);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 删除商品
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void commodityDelButtonAction(ActionEvent e) throws IOException {
        try {
            CommodityDelViewController controller = (CommodityDelViewController) replaceSceneContent(
                    "/view/stockmanager/commodityDel.fxml");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 增加商品
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void commodityAddButtonAction(ActionEvent e) throws IOException {
        try {
            CommodityAddViewController controller = (CommodityAddViewController) replaceSceneContent(
                    "/view/stockmanager/commodityAdd.fxml");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    /**
     * 查找商品
     *
     * @param e
     * @throws IOException
     */
    @FXML
    public void commoditySearchButtonAction(ActionEvent e) throws IOException {
        try {
            CommoditySearchViewController controller = (CommoditySearchViewController) replaceSceneContent(
                    "/view/stockmanager/commoditySearch.fxml");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
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
            LoginController controller = (LoginController) replaceSceneContent(
                    "/view/admin/Login.fxml");
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
        return (Initializable) loader.getController();
    }

    @FXML
    Label idOfCurrentUser, nameOfCurrentUser, categoryOfCurrentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idOfCurrentUser.setText("编号：" + LoginController.getCurrentUser().getID());
        nameOfCurrentUser.setText("姓名：" + LoginController.getCurrentUser().getName());
        categoryOfCurrentUser.setText("身份：" + LoginController.getCategory());

        showClassificationBox(commodityBLService.getRootClassifications());

        CommodityVO commodityVO = commodityInfoService.getCommodity(id_to_modify.trim());
        nameField.setText(commodityVO.getName());
        idLabel.setText(commodityVO.getID());
        importPriceField.setText(String.valueOf(commodityVO.getImportCost()));
        exportPriceField.setText(String.valueOf(commodityVO.getExportCost()));
        typeField.setText(commodityVO.getType());
        alertField.setText(String.valueOf(commodityVO.getAlertNumber()));
        classificationBox.setValue(commodityVO.getClassificationName());
    }

}