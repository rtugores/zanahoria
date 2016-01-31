package huitca1212.cuantotemide;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity2 extends Activity {
	Spinner lista;
	String[]datos= {"Selecciona país...",
			"Andorra","Argentina","Belice","Bolivia","Brasil","Canadá","Chile","Colombia","Costa Rica","Cuba","Ecuador","El Salvador",
			"España","Estados Unidos","Francia","Guatemala","Guinea Ecuatorial","Haití","Honduras","Marruecos","México","Nicaragua","OTRO","Panamá",
			"Paraguay","Perú","Puerto Rico","República Dominicana","Trinidad y Tobago","Uruguay","Venezuela"};
	String seleccion= "Selecciona país...";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		
        
       //================================================================
       //==============CODIGO PARA SPINNER===============================
       //================================================================
      		
        lista=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,datos);
        lista.setAdapter(adaptador);
        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	@Override
        	public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        		seleccion= datos[i];
        	}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
		//================================================================
		//==============CODIGO PARA BOTONES===============================
		//================================================================
		final Button boton1 = (Button)findViewById(R.id.button_inicio); //PRIMER BOTON
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 if (seleccion=="Selecciona país..."){
                	 Toast to = Toast.makeText(getApplicationContext(), "¡Selecciona país!", Toast.LENGTH_SHORT);
                	 to.show();
                 }
                 else{
                     Intent intent = new Intent(MainActivity2.this, Preguntas.class);
                	 String respuesta= seleccion;
                	 Bundle b= new Bundle();
                	 b.putString("SELECCION", respuesta);
                	 intent.putExtras(b);
                	 startActivity(intent);
                 }
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