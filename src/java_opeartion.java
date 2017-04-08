/**
 * Created by Administrator on 2017/4/7.
 */
import java.util.Random;
import java.util.Scanner;
public class java_opeartion {
    private int i=0,j=0;
    Scanner op=new Scanner(System.in);
    String[] re1=new String[2];
    String[] re2=new String[2];
    int r1,r2,r3,r4;

    String huajian(String text){

        if(text.indexOf("/")!=-1) {
            re1 = text.split("/");
            r1 = Integer.parseInt(re1[0]);
            r2 = Integer.parseInt(re1[1]);

            if (r1 == 0)
                return "0";
            else {
                for (int i = 1; i <= r1; i++) {
                    if (r1 % i == 0 && r2 % i == 0) {
                        r1 = r1 / i;
                        r2 = r2 / i;
                        i = 1;
                    }
                    if (r2 == 1)
                        return r1 + "";
                }
                return r1+"/"+r2;
            }

        }
        else
            return text;



    }
    void leixing(){
        int temp;
        int p1,p2,p3,p4;
        char[] c={'+','-','*','/'};
        temp=(int)(Math.random()*2);
        Random random=new Random();
        int index = random.nextInt(c.length);
        if(temp==0){
            p1=(int)(Math.random()*50);
            p2=(int)(Math.random()*50);
            System.out.print(p1);
            System.out.print(c[index]);
            System.out.print(p2);
            opear(Integer.toString(p1),Integer.toString(p2),index);
        }
        if(temp==1){
            p1=(int)(Math.random()*50);
            p2=(int)(Math.random()*50+2);
            p3=(int)(Math.random()*p2+1);
            System.out.print(p1);
            System.out.print(c[index]);
            System.out.print("  "+huajian(p3+"/"+p2)+"   ");
            opear(Integer.toString(p1),huajian(p3+"/"+p2),index);
        }
        if(temp==2){
            p1=(int)(Math.random()*50+2);
            p2=(int)(Math.random()*p1+1);
            p3=(int)(Math.random()*50+2);
            p4=(int)(Math.random()*p3+1);
            System.out.print("  "+huajian(p2+"/"+p1)+"   ");
            System.out.print(c[index]);
            System.out.print("  "+huajian(p4+"/"+p3)+"   ");
            opear(huajian(p2+"/"+p1),huajian(p4+"/"+p3),index);
        }

    }
     void opear(String x,String y,int index){
        int n1,n2,n3,n4,n5,n6;
        String result="";
        String[] aa=new String[2];
        String[] bb=new String[2];
        String[] cc=new String[2];


            if(x.indexOf("/")==-1 && y.indexOf("/")==-1){
                int a=Integer.parseInt(x);
                int b=Integer.parseInt(y);
                if(index==0)
                    result=String.valueOf(a+b);
                if(index==1)
                    result=String.valueOf(a-b);
                if(index==2)
                    result=String.valueOf(a*b);
                if(index==3)
                    result=huajian(a+"/"+b);
            }
            if(x.indexOf("/")==-1 && y.indexOf("/")!=-1){
                int a=Integer.parseInt(x);

                aa=y.split("/");
                n1=Integer.parseInt(aa[0]);
                n2=Integer.parseInt(aa[1]);

                if(index==0)
                    result=huajian((a*n2+n1)+"/"+n2);
                if(index==1)
                    result=huajian((a*n2-n1)+"/"+n2);
                if(index==2)
                    result=huajian((a*n1)+"/"+n2);
                if(index==3)
                    result=huajian((a*n2)+"/"+n1);
            }

            if(x.indexOf("/")!=-1&&y.indexOf("/")!=-1) {
                aa = x.split("/");
                bb = y.split("/");
                n1 = Integer.parseInt(aa[0]);
                n2 = Integer.parseInt(aa[1]);
                n3 = Integer.parseInt(bb[0]);
                n4 = Integer.parseInt(bb[1]);

                if (index == 0)
                    result = huajian((n1 * n4 + n3 * n2) + "/" + (n2 * n4));
                if (index == 1)
                    result = huajian((n1 * n4 - n3 * n2) + "/" + (n2 * n4));
                if (index == 2)
                    result = huajian((n1 * n3) + "/" + (n2 * n4));
                if (index == 3)
                    result = huajian((n1 * n4) + "/" + (n2 * n3));
            }


        System.out.print("=");

        String sum=op.next();
        System.out.print("="+result);
        i++;
        if(result.equals(sum))
        {
            System.out.print("	结果正确    ");
            j++;
        }

        else
            System.out.print("	结果错误    ");

        System.out.println("准确率"+j+"/"+i);



    }
    public static void main(String[] args) {
        java_opeartion aa = new java_opeartion();
        while(true)
        {
/*            if(aa.op.next().equals("exit"))
                break;
            else*/
                 aa.leixing();

        }

    }
}
