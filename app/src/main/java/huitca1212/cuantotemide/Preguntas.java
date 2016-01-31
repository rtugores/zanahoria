package huitca1212.cuantotemide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;


@SuppressLint("DefaultLocale")
public class Preguntas extends Activity {
	
	protected double respuesta;
	protected String pais;

	
	public void consultarPais(){
	//ESCOJO VALOR INICIAL SEGUN PAIS
		Bundle bundle = this.getIntent().getExtras();
	    pais=bundle.getString("SELECCION");
	    pais=pais.toLowerCase();
		respuesta=15.49; //si es otro país
	if(pais.contains("argentina") || pais.contains("andorra") || pais.contains("esp") || pais.contains("chile") || pais.contains("canadá") || pais.contains("belice"))
		respuesta= 14.18;
	if(pais.contains("estados unidos"))
		respuesta=12;
	if(pais.contains("bolivia") || pais.contains("colombia") || pais.contains("venezuela") || pais.contains("ecuador") || pais.contains("guinea ecuatorial"))
		respuesta=17.09;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preguntas);
				
		//================================================================
		//==============SIGUIENTE===============================
		//================================================================
		final Button boton1 = (Button)findViewById(R.id.button_sig); 
		final TextView pregunta = (TextView)findViewById(R.id.primera_ask);
		final RadioButton opcion1= (RadioButton)findViewById(R.id.radio0);
		final RadioButton opcion2= (RadioButton)findViewById(R.id.radio1);
		final RadioButton opcion3= (RadioButton)findViewById(R.id.radio2);	
		
		boton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {	
				//PROCEDO CON LAS PREGUNTAS
				consultarPais();
				if(opcion1.isChecked())  respuesta-=0.5; //respuestas a edad
				if(opcion2.isChecked())  respuesta+=0.4;
				if(opcion3.isChecked())  respuesta+=0;
				opcion1.setChecked(true);
				pregunta.setText("¿Cuánto mides?");
				opcion1.setText("Menos de 1,70 m");
				opcion2.setText("Entre 1,70 y 1,80 m");
				opcion3.setText("Más de 1,80 m");
				boton1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						if(opcion1.isChecked()) respuesta-=0.4; //respuestas a estatura
						if(opcion2.isChecked());
						if(opcion3.isChecked()) respuesta+=0.4;
						opcion1.setChecked(true);
						pregunta.setText("¿De qué color tienes la piel?");
						opcion1.setText("Blanca o casi blanca");
						opcion2.setText("Negra o casi negra");
						opcion3.setText("Entre blanca y negra");
						boton1.setOnClickListener(new View.OnClickListener() {
							public void onClick(View v) {
								if(opcion1.isChecked())  respuesta-=0.1; //respuestas a color piel
								if(opcion2.isChecked())  respuesta+=2;
								if(opcion3.isChecked())  respuesta+=0.6;
								opcion1.setChecked(true);
								pregunta.setText("¿Qué relación tienes entre los dedos índice y anular?");
								opcion1.setText("Es más largo el índice");
								opcion2.setText("Es más largo el anular");
								opcion3.setText("Son igual de largos");
								boton1.setOnClickListener(new View.OnClickListener() {
									public void onClick(View v) {
										if(opcion1.isChecked())  respuesta-=0.2; //respuestas a dedos
										if(opcion2.isChecked())  respuesta-=0.2;
										if(opcion3.isChecked())  respuesta+=0.4;
										opcion1.setChecked(true);
										pregunta.setText("¿Comes habitualmente nueces o arándanos?");
										opcion1.setText("Sí");
										opcion2.setText("No");
										opcion3.setText("A veces");
										boton1.setOnClickListener(new View.OnClickListener() {
											public void onClick(View v) {
												if(opcion1.isChecked())  respuesta+=0.4; //respuestas a comida
												if(opcion2.isChecked())  respuesta-=0.3;
												if(opcion3.isChecked())  respuesta+=0.1;
												opcion1.setChecked(true);
												pregunta.setText("¿Eres fumador?");
												opcion1.setText("Sí");
												opcion2.setText("No");
												opcion3.setText("Sólo de vez en cuando");
												boton1.setOnClickListener(new View.OnClickListener() {
													public void onClick(View v) {
														if(opcion1.isChecked())  respuesta-=0.5; //respuestas al fumar
														if(opcion2.isChecked());
														if(opcion3.isChecked())  respuesta-=0.2;
														opcion1.setChecked(true);
														pregunta.setText("¿Cuántas veces a la semana haces ejercicio?");
														opcion1.setText("Una vez o más");
														opcion2.setText("Sólo hago ejercicio cuando me apetece");
														opcion3.setText("No hago ejercicio");
														boton1.setOnClickListener(new View.OnClickListener() {
															public void onClick(View v) {
																if(opcion1.isChecked()) respuesta+=0.5; //respuestas a ejercicio
																if(opcion2.isChecked()) respuesta+=0.1;
																if(opcion3.isChecked()) respuesta-=0.3;
																Intent intent = new Intent(Preguntas.this, Solucion.class);
																String respuesta_s = Double.toString(respuesta);
																Bundle b= new Bundle();
																b.putString("TAMANYO", respuesta_s);
																intent.putExtras(b);
																startActivity(intent);
																finish();
															}
														}); 
													}
												}); 		
											}
										}); 		
									}	
								}); 	
							}
						}); 
					}
				}); 
			}
		});
       	  	
		//================================================================
		//==============INICIO===============================
		//================================================================
      	
        final Button boton2 = (Button)findViewById(R.id.button_ini); //INICIO
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            		Intent intent = new Intent(Preguntas.this, MainActivity2.class);
            		startActivity(intent);
            }
        });
	}
	
	//================================================================
	//==============CODIGO PARA ACTION BAR============================
	//================================================================
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 //Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		VariasFunciones opcion= new VariasFunciones();
	    switch (item.getItemId()) {
	        case R.id.menu_share:
	            opcion.compartir(this);
	            return true;
	        case R.id.menu_info:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        	builder.setTitle("Información");
	    		builder.setMessage("Aplicación desarrollada por RJ Apps. " +
	    				"Para cualquier sugerencia, no dude en contactar.");
	    		builder.setPositiveButton("Contactar", new OnClickListener() {
	    		    public void onClick(DialogInterface dialog, int which) {
	    		    	Intent i = new Intent(Intent.ACTION_SEND);
	    		    	i.setType("message/rfc822")
	    		    		.putExtra(Intent.EXTRA_EMAIL  , new String[]{"huitca1212@gmail.com"});
	    		    	startActivity(Intent.createChooser(i, "Enviar mediante"));
	    		    }
	    		});
	    		builder.setNegativeButton("Volver", new OnClickListener() {
	    		    public void onClick(DialogInterface dialog, int which) {
	    		       dialog.cancel();
	    		    }
	    		});
	    		builder.show();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	  public void onStart() {
	    super.onStart();
	    EasyTracker.getInstance().activityStart(this);  // Add this method.
	  }

	 @Override
	  public void onStop() {
	    super.onStop();
	    EasyTracker.getInstance().activityStop(this);  // Add this method.
	  }
}