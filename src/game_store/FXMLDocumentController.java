/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_store;
import Dialog.AlertDialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import java.lang.NullPointerException;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.AnchorPane;

/**
 *
 * @author nazif
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btn_addGame;
    @FXML
    private Label label;
    @FXML
    private TextField txt_barcode;
    @FXML
    private TextField txt_gametitle;
    @FXML
    private TextField txt_gametype;
    @FXML
    private TextField txt_price;
    @FXML
    private TextField txt_instock;
    
    
    private Connection con=null;
    private PreparedStatement pst=null;
     //private PreparedStatement gamesequence;
    private ObservableList<GameList>data;
    
     private ResultSet rs=null;
    //int gameId;
    @FXML
    private TableView<GameList> tableGame;
    @FXML
    private TableColumn<?, ?> columnbarcode;
    @FXML
    private TableColumn<?, ?> columngamename;
    @FXML
    private TableColumn<?, ?> columngametype;
    @FXML
    private TableColumn<?, ?> columnprice;
    @FXML
    private TableColumn<?, ?> columninstock;
    @FXML
    private Button btn_UpdateGame;
    @FXML
    private Button btn_DeleteGame;
    @FXML
    private TextField txt_search;
    @FXML
    private Label error_barcode;
    @FXML
    private Label error_gameName;
    @FXML
    private Label error_gameType;
    @FXML
    private Label error_price;
    @FXML
    private Label error_inStock;
    @FXML
    private Button btn_clear;
  
   
    
    @FXML
    private void handleAddGame(ActionEvent event) throws SQLException {
        boolean isBarcodeEmpty=validation.TextFieldValidation.istextFieldTypeNumber(txt_barcode,error_barcode,"Barcode must be a number");
        boolean isGameNameEmpty=validation.TextFieldValidation.isTextFieldNotEmpty(txt_gametitle,error_gameName, "Game Name is required");
        boolean isGameTypeEmpty=validation.TextFieldValidation.isTextFieldNotEmpty(txt_gametype, error_gameType, "Game Type is required");
        boolean isPriceEmpty=validation.TextFieldValidation.istextFieldTypeNumber(txt_price, error_price,"Price must be number ");
        boolean isInStockEmpty=validation.TextFieldValidation.istextFieldTypeNumber(txt_instock, error_inStock, "In stock must be a number ");
        if (isBarcodeEmpty && isGameNameEmpty && isGameTypeEmpty && isPriceEmpty && isInStockEmpty){
        String sql="insert into games (barcode,gamename,gametype,price, instock) values (?, ?, ?, ?,?)";
        //sql="select game_sq.nextval from dual";
        //Integer gameId=gs.getInt(1);
        String barcode=txt_barcode.getText();
        String gametitle=txt_gametitle.getText();
        String gametype=txt_gametype.getText();
        double price=Double.valueOf(txt_price.getText());
        Integer instock=Integer.valueOf(txt_instock.getText());
        
              
        try {
            //gs = gamesequence.executeQuery(sqls);
            pst=con.prepareStatement(sql);
             //gamesequence=con.prepareStatement(sqls);
             //pst.setInt(1, gameId);
            pst.setString(1, barcode);
            pst.setString(2, gametitle);
            pst.setString(3, gametype);
            pst.setDouble(4,price);
            pst.setInt(5, instock);
             //gs=pst.executeQuery();
            
           int i= pst.executeUpdate();
           if (i==1)
                //System.out.println("Data Inserted Successfully !");
            AlertDialog.display("Info", "Data Inserted Sucessfully !");
           setCellTable();
           loadDataFromDatabase();
           clearFields();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            pst.close();
        }
        }
    }
    //performs clears after data is inserted to the table
    private void clearFields(){
       txt_barcode.setText(null);
       txt_gametitle.setText(null);
       txt_gametype.setText(null);
       txt_price.setText(null);
       txt_instock.setText(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        con=dba.DBConnection.gameConnection();
        data=FXCollections.observableArrayList();
        setCellTable();
        loadDataFromDatabase();
        searchGame();
       
        
    }    
    private void setCellTable(){
        columnbarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        columngamename.setCellValueFactory(new PropertyValueFactory<>("gameName"));
        columngametype.setCellValueFactory(new PropertyValueFactory<>("gameType"));
        columnprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        columninstock.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        
        
        
    }
    //this method loads the data from the SQL table
    private void loadDataFromDatabase(){
        data.clear();
        try {
            pst=con.prepareStatement("select * from games");
            rs=pst.executeQuery();
            while (rs.next()){
                data.add(new GameList(rs.getString(1),rs.getString(2),rs.getString(3),""+ rs.getDouble(4), ""+rs.getInt(5)));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableGame.setItems(data);
        
    }
    //method simply updates a product in the games table
    @FXML
    private void handleUpdateGame(ActionEvent event) {
        //The product will be updated using barcode
        String sql="update games set gamename=?,gametype=?, price=?,instock=? where barcode=?";
        try {
            String barcode=txt_barcode.getText();
        String gametitle=txt_gametitle.getText();
        String gametype=txt_gametype.getText();
        double price=Double.valueOf(txt_price.getText());
        Integer instock=Integer.valueOf(txt_instock.getText());
            pst=con.prepareStatement(sql);
            
            pst.setString(1, gametitle);
            pst.setString(2, gametype);
            pst.setDouble(3,price );
            pst.setInt(4, instock);
             pst.setString(5, barcode);
           
            int i=pst.executeUpdate();
            if (i==1){
                AlertDialog.display("Info", "Game Updated Sucessfully");
                loadDataFromDatabase();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // method that add product to the table
    @FXML
    private void handleDeleteGame(ActionEvent event) {
        String sql="delete from games where barcode=?";
        try {
            pst=con.prepareStatement(sql);
            pst.setString(1,txt_barcode.getText());
            int i=pst.executeUpdate();
            if (i==1){
              AlertDialog.display("Info", "Game Deleted Sucessfully");
                loadDataFromDatabase();  
            }
                    
          } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //the method that search SQL
    private void searchGame(){
        txt_search.setOnKeyReleased(e->{
            if (txt_search.getText().equals("")){
               loadDataFromDatabase();
            } 
            else {
                data.clear();
            String sql="select * from games where barcode LIKE '%"+txt_search.getText()+"%'"
                    +"UNION select * from games where gameName LIKE '%"+txt_search.getText()+"%'"
                    +"UNION select * from games where gameType LIKE '%"+txt_search.getText()+"%'"
                    +"UNION select * from games where inStock LIKE '%"+txt_search.getText()+"%'";
            try {
                pst=con.prepareStatement(sql);
                //pst.setString(1, txt_search.getText());
                rs=pst.executeQuery();
                if (rs.next()){
                    System.out.println(""+rs.getString(1));
                 data.add(new GameList(rs.getString(1),rs.getString(2),rs.getString(3),""+ rs.getDouble(4), ""+rs.getInt(5)));
                }
                tableGame.setItems(data);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
        });
    }

    //This is the event for Clear. Which will clear all the fields
    @FXML
    private void handleClear(ActionEvent event) {
        
       txt_barcode.setText(null);
       txt_gametitle.setText(null);
       txt_gametype.setText(null);
       txt_price.setText(null);
       txt_instock.setText(null);
    }
}
