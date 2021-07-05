package com.data.gc;

public class HelpSelf {
    private static HelpSelf hs=null;

    @Override
    protected void finalize() throws Throwable {/*对象第一次被回收的时候调一次,第二次就不会了*/
        super.finalize();
        System.out.println("finalize");
        //有一次自救机会
        hs=this;
    }

    public static void main(String[] args) throws InterruptedException {
        hs=new HelpSelf();

        hs=null;
        System.gc();/*第一次 调用finalize*/
        Thread.sleep(1000L);
        System.out.println("1 "+hs);

        hs=null;
        System.gc();/*第二次不再调用*/
        Thread.sleep(1000L);
        System.out.println("2 "+hs);
    }
}
