package EFAXPRODUTO;


import entidades.produtos;
import View.viewProduto;
import View.viewSede;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import DAO.SedeDAO;
import entidades.produtos;
import DAO.ProdutoDAO;
import entidades.Sede;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLControleProduto implements Initializable {

	private viewProduto viewProduto = new viewProduto();
    private ProdutoDAO ProdutoDAO = new ProdutoDAO();
    private ArrayList<produtos> listaProdutos = new ArrayList<produtos>();	
    private produtos produtoEscolhido;
    private SedeDAO SedeDAO = new SedeDAO(); 
    private viewSede viewSede = new viewSede();
    private Sede sedeEscolhida;
	private ArrayList<Sede> listaSede = new ArrayList<Sede>();  
    private ArrayList<produtos> listarProdutos = new ArrayList<produtos>();
    
    @FXML
    private TextField TXTNOME;
      
    @FXML
    private TextField txtbairro;

    @FXML
    private TextField txtcep;

    @FXML
    private TextField TXTGENERO;

    @FXML
    private TextField TXTCORREDOR;

    @FXML
    private TableView<produtos> table;

    @FXML
    private TableColumn<?, ?> ID;

    @FXML
    private TableColumn<?, ?> Nome;

    @FXML
    private TableColumn<?, ?> Genero;

    @FXML
    private TableColumn<?, ?> Corredor;

    @FXML
    private Button BTNINSERIR; 
    
    @FXML
    private Button BTNALTERAR;

    @FXML
    private Button BTNEXCLUIR;

    @FXML
    private Button BTNPESQUISAR;

    @FXML
    private Button BTNSAIR;
    
    @FXML
    private TableView<Sede> TABLESEDE;
    
    @FXML
    private TableColumn<?, ?> COLBAIRRO;

    @FXML
    private TableColumn<?, ?> COLCEP;

    @FXML
    private TextField TXTPESQUISAR;
    
    public void initialize(URL location, ResourceBundle resources) {
	    ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		Nome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		Genero.setCellValueFactory(new PropertyValueFactory<>("Genero"));
		Corredor.setCellValueFactory(new PropertyValueFactory<>("Corredor"));

		refreshTable();

		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<produtos>() {

			@Override
			public void changed(ObservableValue<? extends produtos> observable, produtos oldValue, produtos newValue) {
				produtoEscolhido = newValue;
			}
		});
		
		COLBAIRRO.setCellValueFactory(new PropertyValueFactory<>("Bairro"));
		COLCEP.setCellValueFactory(new PropertyValueFactory<>("Cep"));

		refreshTableSede();

		TABLESEDE.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Sede>() {

			@Override
			public void changed(ObservableValue<? extends Sede> observable, Sede oldValue, Sede newValue) {
				sedeEscolhida = newValue;
			}
		});
		
	}
    @FXML
    private void cadastrar(ActionEvent event) {
    	
    	produtos Produtos = viewProduto.inserir(TXTNOME.getText(), TXTGENERO.getText(), TXTCORREDOR.getText() );
    	Sede Sede = viewSede.inserir(txtbairro.getText(), txtcep.getText());
    	SedeDAO.inserirSede(Sede);
    	Produtos.setSede(Sede);
    	ProdutoDAO.inserirProduto(Produtos);
    	
    	refreshTable();
    	refreshTableSede();

    }
       
    @FXML
    private void refreshTable() {
		listaProdutos = new ProdutoDAO().listarProdutos();
		ObservableList<produtos> observableList = FXCollections.observableArrayList(listaProdutos);
		table.setItems(observableList);
		table.refresh();
	}  
    
    @FXML
    private void refreshTableSede() {
		listaSede = new SedeDAO().listarSede();
		ObservableList<Sede> observableListSede = FXCollections.observableArrayList(listaSede);
		TABLESEDE.setItems(observableListSede);	
		TABLESEDE.refresh();
	}  
    
    @FXML
	 private void deletar() {
			ProdutoDAO.excluirProdutos(produtoEscolhido);
			SedeDAO.excluirSede(sedeEscolhida);
			refreshTable();	
			refreshTableSede();
	 }
    
    @FXML
	 private void editarProduto() {
		 TXTNOME.setText(produtoEscolhido.getNome());
		 TXTGENERO.setText(produtoEscolhido.getGenero());
		 TXTCORREDOR.setText(produtoEscolhido.getCorredor());	
		 
		 ProdutoDAO.alterarProdutos(produtoEscolhido);
		 refreshTable();	
		 refreshTableSede();
	 }
    
    @FXML
    private void editar() {
    	 produtoEscolhido.setNome(TXTNOME.getText());
    	 produtoEscolhido.setGenero(TXTGENERO.getText());
    	 produtoEscolhido.setCorredor(TXTCORREDOR.getText());	
    	 
		 ProdutoDAO.alterarProdutos(produtoEscolhido);
		 refreshTable();	
		 refreshTableSede();
	 }
    @FXML
   private  void sair(ActionEvent event ) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Sair");
    	String s ="Pow, Para que Sair do meu trabalho ..... Vou ficar triste, Fica mais um pouco!";
    	alert.setContentText(s);
    	
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if((result.isPresent()) && (result.get() == ButtonType.OK)){
    		System.exit(0);
    	
    }
    }
    @FXML
	 private void pesquisar() {
		 
		 ProdutoDAO ProdutoPesquisar = new ProdutoDAO();
		
		 produtos pesquisar = new produtos();
		 
		 pesquisar = ProdutoPesquisar.pesquisar(TXTPESQUISAR.getText());
		 
		 listarProdutos.add(pesquisar);
		 		 
		 ObservableList<produtos> observableList = FXCollections.observableArrayList(pesquisar);
		 	 
		 table.setItems(observableList);
		 
	 }


}
