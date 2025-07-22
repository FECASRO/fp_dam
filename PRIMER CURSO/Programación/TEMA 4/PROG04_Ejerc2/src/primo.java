import java.util.Scanner;

/** 
 * En esta clase contiene el ejercicio 2 de programacion se piden cinco numeros 
 * y se calcula si son numeros primos o no
 * @author Felipe Castillo Rodriguez
 * @version 1.0 
 */


public class primo {

	public static void main(String[] args) {
	
//se llama al scanner y se inicializan variables  		
		
		Scanner scn=new Scanner(System.in);
		System.out.println("Introduce un numero entero positivo: ");
		int a = 0,i,num1;
		num1 = scn.nextInt();
	//si se introduce un numero menor que uno no se permite	
		
		if (num1<0) {
			System.out.println(" Este numero es negativo y no permitido ");
		}else  
		
	/**aqui comienza el bucle si el numero tiene mas de dos divisores no es primo puesto 
	 * que el numero primo solo se puede dividir por si mismo y la unidad  */	
		for (i=1;i<=num1;i++) {
			
			if (num1 % i ==0) {
				
				a++;
			}
			
		}
		
		if (a!=2) {
			
			System.out.println(num1+ " No es primo");
		} else {
			
			System.out.println(num1 + " Si es primo");
		}
		
		
		System.out.println("vamos a por el segundo: ");
		int num2 =scn.nextInt();
		int b = 0,j;
			if (num2<0) {
			System.out.println(" Este numero es negativo y no permitido ");
		}else 
		
//mismo bucle con el numero 2º
	
for (j=1;j<=num2;j++) {
			
			if (num2 % j ==0) {
				
				b++;
			}
			
		}
		
		if (b!=2) {
			
			System.out.println(num2+ " No es primo");
		} else {
			
			System.out.println(num2 + " Si es primo");
		}
		System.out.println("vamos a por el tercero: ");
		int num3 =scn.nextInt();
		
		int c = 0,h;
		if (num3<0) {
		System.out.println(" Este numero es negativo y no permitido ");
	}else 
	
//mismo bucle con el numero tercero
		
for (h=1;h<=num3;h++) {
		
		if (num3 % h ==0) {
			
			c++;
		}
		
	}
	
	if (c!=2) {
		
		System.out.println(num3+ " No es primo");
	} else {
		
		System.out.println(num3 + " Si es primo");
	}
		System.out.println("vamos a por el cuarto: ");
		int num4 =scn.nextInt();
		
		int d = 0,k;
		if (num4<0) {
		System.out.println(" Este numero es negativo y no permitido ");
	}else 
	

//mismo bucle con el cuarto numero		
		
for (k=1;k<=num4;k++) {
		
		if (num4 % k ==0) {
			
			d++;
		}
		
	}
	
	if (d!=2) {
		
		System.out.println(num4+ " No es primo");
	} else {
		
		System.out.println(num4 + " Si es primo");
	}
		
		System.out.println("vamos a por el quinto: ");
		int num5 =scn.nextInt();
		int e = 0,m;
		if (num5<0) {
		System.out.println(" Este numero es negativo y no permitido ");
	}else 
	

//quinto y ultimo numero con este se acaba el programa y por eso el runFinalization del final		
		
for (m=1;m<=num5;m++) {
		
		if (num5 % m ==0) {
			
			e++;
		}
		
	}
	
	if (e!=2) {
		
		System.out.println(num5+ " No es primo");
	} else {
		
		System.out.println(num5 + "Si es primo");
		System.runFinalization();
	}
		

	}
}

