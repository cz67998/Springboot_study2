package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@ComponentScan("com.example.demo")
//这东西用于监听器
@ServletComponentScan(basePackages ="com.example.demo.*")
@MapperScan(basePackages = "com.example.demo.mybatis.mapper")
@EnableScheduling
@EnableTransactionManagement
public class DemoApplication {

	public static void main(String[] args) {
		//java -jar blockchain.jar 后面的属性
//		if(args!=null&&(args.length==2||args.length==3)){
//			try{
//				int htttport=Integer.valueOf(args[0]);
//				int p2pPort=Integer.valueOf(args[1]);
//				BlockChain blockChainService=new BlockChain();
//				P2PService p2PService=new P2PService(blockChainService);
//				p2PService.initP2PServer(p2pPort);
//				if(args.length==3&&args[2]!=null){
//					p2PService.connectToPeer(args[2]);
//				}
//			}catch (Exception e){
//				System.out.println("startup is error:"+e.getMessage());
//			}
//		}else {
//			System.out.println("usage:java -jar blockchain.jar "+args[0]+" "+args[1]);
//		}
		SpringApplication.run(DemoApplication.class, args);

	}


}
