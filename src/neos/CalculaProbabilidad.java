package neos;

public class CalculaProbabilidad {
	

	public static String probabilidad(String neo) {
		String[] neos = neo.split(",");
		double resultado = 0;
		
		

        //CALCULAR PROBABILIDADES NEOS

         //metodo para calcular
		
         double posicionNEO = Double.parseDouble(neos[1]);
         double velocidadNEO = Double.parseDouble(neos[2]);
         double posicionTierra = 1;
         double velocidadTierra = 100;
         for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
            posicionNEO = posicionNEO + velocidadNEO * i;
            posicionTierra = posicionTierra + velocidadTierra * i;
         
         resultado = 100 * Math.random() *
         Math.pow( ((posicionNEO-posicionTierra)/(posicionNEO+posicionTierra)), 2);
         System.out.println(resultado);	
         }
         
         //creamos un string donde almacenamos el nombre del neo y la probabilidad(resultado)
         String probabilidadNeo = neos[0] + "," + resultado;
         
		return probabilidadNeo;

	}
}
