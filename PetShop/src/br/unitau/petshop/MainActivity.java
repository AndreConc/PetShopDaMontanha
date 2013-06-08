package br.unitau.petshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cadastro.R;

public class MainActivity extends Activity {	
   
	
	
	RegCad primeiroReg,registro,ultimoReg,auxiliar;
	EditText campoNome,campoEndereco,campoTelefone,campoRaca;
	int qtdRegistros,posicao;
	
	Button btnCadastro,btnMostra,btnCadastrar,btnVoltar,btnSair,btnProximo,
	btnAnterior,btnSobre,btnAgenda,btnValor,btnDesenvolvedor,btnRetorna,btnFinaliza,btnDataHora;
	
	TextView mostraNome, mostraTelefone, mostraEndereco, mostraRaca;
	
	 protected void mostraCxTexto(String msg, String titulo) {
		 
		
		    	 
			// Gera uma caixa de texto na tela com um botão "OK"
			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setMessage(msg);
			builder.setNeutralButton("OK", null);
			AlertDialog dialog = builder.create();
			dialog.setTitle(titulo);
			dialog.show(); 
			
	 }
	 

	 
	 
	 void inicializaObjetos() {
			//Inicializa os objetos das views utilizando 
	 		//o try/catch para evitar force-close 

			//Objetos do layout principal
			try{
		          btnCadastro = (Button) findViewById(R.id.btnCadastro);		        
		          btnSair = (Button) findViewById(R.id.btnSair);
		          btnSobre = (Button) findViewById(R.id.btnSobre);
		          btnAgenda = (Button) findViewById(R.id.btnAgenda);
		          btnValor  = (Button) findViewById(R.id.btnValor);
		          btnDesenvolvedor = (Button) findViewById(R.id.btnDesenvolvedor);
		          
			} catch (Exception e){
			   // Não faz nada
			}
			
						
			//objetos do layout de cadastramento
			try{
			   campoNome     = (EditText)findViewById(R.cad.nome);
			   campoEndereco = (EditText)findViewById(R.cad.endereco);
			   campoRaca     = (EditText)findViewById(R.cad.raca);
			   campoTelefone = (EditText)findViewById(R.cad.telefone);
			   btnCadastrar  = (Button)  findViewById(R.cadastro.btnCadastrar);
			   btnSair       = (Button)  findViewById(R.cadastro.btnSair);
			   btnMostra     = (Button)  findViewById(R.cadastro.btnMostra);
			} catch (Exception e){
			   // Não faz nada
			}
			
			
			
			
			//objetos do layout de visualização de registros 
			try{
	            	   mostraNome     = (TextView) findViewById(R.mostra.nome);
	            	   mostraTelefone = (TextView) findViewById(R.mostra.telefone);
	            	   mostraEndereco = (TextView) findViewById(R.mostra.endereco);
	            	   mostraRaca     = (TextView) findViewById(R.mostra.raca);
		               btnAnterior    = (Button)   findViewById(R.lista.btnAnterior);
		               btnProximo     = (Button)   findViewById(R.lista.btnProximo);
		               btnVoltar      = (Button)   findViewById(R.lista.btnVoltar);
			} catch (Exception e){
			   // Não faz nada
			}
		}


		void carregaListeners() {
			//inicializa os listeners dos objetos
	 		//utilizando o try/catch 
			//para evitar force-close da aplicação
			
			
			
			//listeners do layout cadastro, agenda, valor, desenvolvedor, sair, sobre
			try{			  
				btnCadastro.setOnClickListener(new View.OnClickListener() {
			      @Override
				public void onClick(View arg0) {
				iniciaCadastro();
			      }
			  });
				
				btnSobre.setOnClickListener(new View.OnClickListener() {
				      @Override
					public void onClick(View arg0) {
					iniciaSobre();
				      }
				  });
				btnAgenda.setOnClickListener(new View.OnClickListener() {
				      @Override
					public void onClick(View arg0) {
					iniciaAgenda();
				      }
				  });	
				btnDesenvolvedor.setOnClickListener(new View.OnClickListener() {
				      @Override
					public void onClick(View arg0) {
					iniciaDesenvolvedor();
				      }
				  });
				btnValor.setOnClickListener(new View.OnClickListener() {
				      @Override
					public void onClick(View arg0) {
					iniciaValor();
				      }
				  });	
						  
			} catch (Exception e){
		 	   // Não faz nada
	}
			
			

			//listeners do layout de cadastramento
			try{
			  btnCadastrar.setOnClickListener(new 
		 	    View.OnClickListener() {
			      @Override
				public void onClick(View arg0) {
				try {
				  insereRegistro();
				  mostraCxTexto("Cadastro efetuado com sucesso", "Aviso");
			   	  campoEndereco.setText(null);
				  campoTelefone.setText(null);
				  campoRaca.setText(null);
				  campoNome.setText(null);
				  campoNome.requestFocus();
				} catch (Exception e) {
				  mostraCxTexto("Erro ao cadastrar", 
	 					  "Erro");
				}
			      }
			  });
			  
			  btnSair.setOnClickListener(new 
	 		    View.OnClickListener() {
			      @Override
				public void onClick(View arg0) {
				iniciaAplicacao();
			      }
			  });
			  btnMostra.setOnClickListener(new 
				 	    View.OnClickListener() {
					      @Override
						public void onClick(View arg0) {
						iniciaMostrarLista();
					      }
					  });	
			} catch (Exception e){
			   // Não faz nada
	}

			//listeners do layout de sobre
			try{						        
			  btnSair = (Button) findViewById(R.sobre.btnSair);	
			  		  
			  btnSair.setOnClickListener(new 
	 		    View.OnClickListener() {
			      @Override
				public void onClick(View arg0) {
				iniciaAplicacao();
			      }
			  });			  
			} catch (Exception e){
			   // Não faz nada
	}
			
			//listeners do layout de agenda para voltar a tela principal
			try{						        
			  btnSair = (Button) findViewById(R.agenda.btnSair);	
			  btnSair.setOnClickListener(new 
	 		    View.OnClickListener() {
			      @Override
				public void onClick(View arg0) {
				iniciaAplicacao();
			      }
			  });
		  
			} catch (Exception e){
			   // Não faz nada
	}
			
			//listeners do layout de agenda refernte ao bot�o Abrir Site para agendamento
			try{						        
			  btnDataHora = (Button) findViewById(R.agenda.bntDataHora);	
			  btnDataHora.setOnClickListener(new 
	 		    View.OnClickListener() {
			      @Override
				public void onClick(View arg0) {
			    	  Uri uri = Uri.parse("http://www.php-calendar.com/php-calendar/");
			          Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			          startActivity(intent);
				iniciaAplicacao();
			      }
			      
			  });
		  
			} catch (Exception e){
			   // Não faz nada
	}
			
			//listeners do layout de valor
			try{						        
			  btnSair = (Button) findViewById(R.valor.btnRetorna);	
			  		  
			  btnSair.setOnClickListener(new 
	 		    View.OnClickListener() {
			      @Override
				public void onClick(View arg0) {
				iniciaAplicacao();
			      }
			  });			  
			} catch (Exception e){
			   // Não faz nada
	}
			
			//listeners do layout de desenvolvedor
			try{						        
			  btnSair = (Button) findViewById(R.desenvolvedor.btnRetorna);	
			  		  
			  btnSair.setOnClickListener(new 
	 		    View.OnClickListener() {
			      @Override
				public void onClick(View arg0) {
				iniciaAplicacao();
			      }
			  });			  
			} catch (Exception e){
			   // Não faz nada
	}
			
			
			
			//Finaliza a aplica��o
			try{						        
				 btnSair = (Button)findViewById(R.id.btnSair);
				    btnSair.setOnClickListener(new android.view.View.OnClickListener() {
				        @Override
				        public void onClick(View v) {
				            System.runFinalizersOnExit(true);
				            finish();
				        }
				    });		  
			} catch (Exception e){
			   // Não faz nada
	}
			
					
			
			//listeners do layout de visualização de registros
	 		try{
		         btnVoltar.setOnClickListener(new 
	 		   View.OnClickListener(){
		             @Override
					public void onClick(View arg0){
		                 iniciaAplicacao();
		             }
	                });
		         btnAnterior.setOnClickListener(new 
	 		   View.OnClickListener(){
		             @Override
					public void onClick(View arg0){
		            	 mostraRegAnterior();
		             }
		         });

		         btnProximo.setOnClickListener(new 
	 		   View.OnClickListener(){
		             @Override
					public void onClick(View arg0){
		            	 mostraRegProximo();
		             }
		         });
			} catch (Exception e){
			   // Não faz nada
			}
		}
			
		
        //Inicia todas os layout 
		void iniciaAplicacao() {
			//chama o layout principal
			setContentView(R.layout.main);
	        	inicializaObjetos();
	        	carregaListeners();
		}

		void iniciaCadastro() {
			//muda para o layout de cadastramento
			setContentView(R.layout.cadastro);
	        	inicializaObjetos();
	        	carregaListeners();
			campoNome.requestFocus();
		}
		
		void iniciaSobre() {
			//muda para o layout de sobre
			setContentView(R.layout.sobre);
	        	inicializaObjetos();
	        	carregaListeners();	
		}
		
		void iniciaAgenda() {
			//muda para o layout de agenda
			setContentView(R.layout.agenda);
	        	inicializaObjetos();
	        	carregaListeners();	
		}
		void iniciaDesenvolvedor() {
			//muda para o layout de desenvolvedor
			setContentView(R.layout.desenvolvedor);
	        	inicializaObjetos();
	        	carregaListeners();	
		}
		void iniciaValor() {
			//muda para o layout de valor
			setContentView(R.layout.valor);
	        	inicializaObjetos();
	        	carregaListeners();	
		}
		
		
				
		
		//verifica a existencia de registros e muda para o layout de visualização
		void iniciaMostrarLista() {			
	        	if(qtdRegistros==0) {
			  mostraCxTexto("Nenhum registro cadastrado", 
		 			  "Aviso");
	            	  iniciaAplicacao();
	            	  return;
	        	} else {
		          setContentView(R.layout.mostralista);
		          inicializaObjetos();
		          carregaListeners();
		        
		          posicao=1;
		          auxiliar=primeiroReg;
		          mostraRegistro();
	        	}
	    	}
		
		
		//Mostra nos campos da tela os dados do registro
		protected void mostraRegistro() {			  
		          mostraNome.setText(auxiliar.nome);
		          mostraTelefone.setText(auxiliar.telefone);
		          mostraEndereco.setText(auxiliar.endereco);
		          mostraRaca.setText(auxiliar.raca);
			}
		
		
		//não faz nada se estiver no ultimo registro
		protected void mostraRegProximo() {	  	  
		  if(posicao!=qtdRegistros) {
		      posicao++;
		      auxiliar=auxiliar.Prox;
		      mostraRegistro();
		  }
		}
		
		
		//não faz nada se estiver no primeiro registro
		protected void mostraRegAnterior() {	 	   
	       if(posicao!=1) {  
	           posicao--;
	           auxiliar=auxiliar.Ant;
	           mostraRegistro();
	       }
		}

		protected void insereRegistro() {
			//Gera novo registro
			registro = new RegCad();
			//Pega dados digitados
			registro.nome =
	 			      campoNome.getText().toString();
			registro.endereco = 
	 			  campoEndereco.getText().toString();
			registro.telefone = 
		 		  campoTelefone.getText().toString();
			registro.raca =
					campoRaca.getText().toString();
			//Trata ordem dos registros
			if (primeiroReg == null)
				primeiroReg = registro;
				registro.Ant = ultimoReg;
			if (ultimoReg == null)
				ultimoReg = registro;
			else {
				ultimoReg.Prox = registro;
				ultimoReg = registro;
			}
			qtdRegistros++;
		}
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qtdRegistros=0;
        primeiroReg = ultimoReg = null;
        iniciaAplicacao();
        
    }
    
}   


