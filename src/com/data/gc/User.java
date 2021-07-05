package com.data.gc;

public class User {
    private byte[]bs=new byte[100*1024];
    private String userId;

    public User(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "userId='" + userId;
    }

    @Override
    protected void finalize() throws Throwable {/*对象第一次被回收的时候调一次,类似于析构方法，可以自救一次*/
        super.finalize();
        System.out.println("finalize userId=="+userId);
    }
}
