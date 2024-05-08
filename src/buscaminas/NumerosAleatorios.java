package buscaminas;

public class NumerosAleatorios {
    
    public static int[] generarAleatorios(int des, int has){ // Genera un grupo de números desordenados enteros diferentes en un rango.
        int rango = Math.abs(has - des);
        ArregloInt[] grupo = new ArregloInt[rango];
        
        for(int i=0;i<grupo.length;i++){
            grupo[i] = new ArregloInt();
            grupo[i].ponNum(i + Math.min(des, has));
            grupo[i].ponSel(false);
        }
        
        int[] enteros = new int[rango];
        
        for(int j=0;j<enteros.length;j++){
            int ub;
            do{
                ub = (int)(Math.random()*rango);
            }while(grupo[ub].darSel());
            enteros[j] = grupo[ub].darNum();
        }
        
        return enteros;
    }
    
    public static int[] generarAleatorios(int cuantos, int des, int has){ // Genera un arreglo de números aleatorios
        int rango = Math.abs(has - des);
        ArregloInt[] grupo = new ArregloInt[rango];
        
        for(int i=0;i<grupo.length;i++){
            grupo[i] = new ArregloInt();
            grupo[i].ponNum(i + Math.min(des, has));
            grupo[i].ponSel(false);
        }
        
        int[] enteros = new int[cuantos];
        
        for(int j=0;j<enteros.length;j++){
            int ub;
            do{
                ub = (int)(Math.random()*rango);
            }while(grupo[ub].darSel());
            enteros[j] = grupo[ub].darNum();
        }
        
        return enteros;
    }
    
    private static class ArregloInt{
        private int num;
        private boolean sel;
        
        public int darNum(){
            return num;
        }
        
        public boolean darSel(){
            return sel;
        }
        
        public void ponNum(int num){
            this.num = num;
        }
        
        public void ponSel(boolean sel){
            this.sel = sel;
        }
    }
}
