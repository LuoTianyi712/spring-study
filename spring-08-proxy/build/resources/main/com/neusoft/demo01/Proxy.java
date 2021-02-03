package com.neusoft.demo01;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Proxy implements Rent {
    private Host host;

    @Override
    public void rent() {
        host.rent();
        seeHouse();
        fare();
        contract();
    }

    // 看房
    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    // 收中介费
    public void fare(){
        System.out.println("收中介费");
    }

    // 合同
    public void contract(){
        System.out.println("签合同");
    }
}
