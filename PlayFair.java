public class PlayFair{
    public static String preprocess(String s){
        s=s.toLowerCase();
        s=s.replace(" ","");
        return s;
    }
    public static char[][] gentable(String key,char[][] table) {
        int ind=0,ascii=97;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(ind<key.length()){
                    table[i][j]=key.charAt(ind);
                    ind++;
                }
                else{
                    //System.out.println((char)ascii);
                    while(key.contains((char)ascii+"")){
                        ascii++;  
                    }
                    if((char)ascii=='j') {
                        ascii++;
                    }
                    table[i][j]=(char)ascii;
                    ascii++;
                }
            }
        }
        return table;
    }
    public static String encrypt(char[][] table, String pt){
        String p=pt,ct=new String();
        int start=0,end=2,firsti=-1,firstj=-1,scndi=-1,scndj=-1;
        while(end<=pt.length()){
            p=pt.substring(start,end);
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(p.charAt(0)==table[i][j]){
                        firsti=i;
                        firstj=j;
                    }
                    if(p.charAt(1)==table[i][j]){
                        scndi=i;
                        scndj=j;
                    }
                }
            }
            if(firsti==scndi){
                if(firstj==4) firstj=-1;
                else if(scndj==4) scndj=-1;
                ct+=(char)table[firsti][firstj+1]+""+(char)table[scndi][scndj+1];
            }
            else if(firstj==scndj){
                if(firsti==4) firsti=-1;
                else if(scndi==4) scndi=-1;
                ct+=(char)table[firsti+1][firstj]+""+(char)table[scndi+1][scndj];
            }
            else{
                ct+=(char)table[firsti][scndj]+""+(char)table[scndi][firstj];
            }
            start+=2;
            end+=2;
           // System.out.println(p+"  "+table[firsti][scndj]+""+table[scndi][firstj]);
        }
        return ct;
    }
    public static String decrypt(char[][] table, String pt){
        String p=pt,ct=new String();
        int start=0,end=2,firsti=-1,firstj=-1,scndi=-1,scndj=-1;
        while(end<=pt.length()){
            p=pt.substring(start,end);
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(p.charAt(0)==table[i][j]){
                        firsti=i;
                        firstj=j;
                    }
                    if(p.charAt(1)==table[i][j]){
                        scndi=i;
                        scndj=j;
                    }
                }
            }
            if(firsti==scndi){
                if(firstj==0) firstj=5;
                else if(scndj==0) scndj=5;
                ct+=(char)table[firsti][firstj-1]+""+(char)table[scndi][scndj-1];
            }
            else if(firstj==scndj){
                if(firsti==0) firsti=5;
                else if(scndi==0) scndi=5;
                ct+=(char)table[firsti-1][firstj]+""+(char)table[scndi-1][scndj];
            }
            else{
                ct+=(char)table[firsti][scndj]+""+(char)table[scndi][firstj];
            }
            start+=2;
            end+=2;
           // System.out.println(p+"  "+table[firsti][scndj]+""+table[scndi][firstj]);
        }
        return ct;
    }
    public static void main(String[] args){
        String pt="instruments";
        if(pt.length()%2!=0) pt+="z";
        String key="MONARCHY";
        String processedpt=preprocess(pt);
        String processedkey=preprocess(key);
        char [][] table=new char[5][5];
        table=gentable(processedkey,table);
        String ct=encrypt(table,pt);
        System.out.println("Encrypted Cipher Text: "+ct);
        String ect=decrypt(table,ct);
        if(ect.charAt(ect.length()-1)=='z') ect=ect.substring(0,ect.length()-1);
        System.out.println("Decrypted Plain Text: "+ect);
    }
} 
