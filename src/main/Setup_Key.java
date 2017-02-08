package main;

import it.unisa.dia.gas.jpbc.*;   
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.plaf.jpbc.pairing.a.TypeACurveGenerator;

public class Setup_Key implements Interface{
	private Element z, z1, z2;
	private Element g, g1, g2;
	private Element gt, gt1, gt2;
	private int i;
	public int n;
    private Field G1, GT, Zr;  
    private Pairing pairing; 
    private Boolean bool;
    
    //接口调用入口
    public Setup_Key()
    {
    	n = 1000;
    	init();
    }  
    
	//初始化 
    private void init()
    {	
    	System.out.println("-------------------系统初始化----------------------");
    	//生成A型曲线双线性群
    	TypeACurveGenerator pg = new TypeACurveGenerator(160, 512);
    	PairingParameters typeAParams = pg.generate();
    	pairing = PairingFactory.getPairing(typeAParams);
    	PairingFactory.getInstance().setUsePBCWhenPossible(true);  
    	System.out.println("A型曲线双线性群 生成成功");
    	
    	//将变量初始化为Zr中的元素  
        Zr = pairing.getZr();  
        z1 = Zr.newRandomElement().getImmutable();
        z2 = Zr.newRandomElement().getImmutable();
        z = Zr.newElement();
        System.out.println("z1=" + z1); 
        System.out.println("z2=" + z2); 
        
        //将变量初始化为G1中的元素，G1是加法群  
        G1 = pairing.getG1();
        g1 = G1.newRandomElement().getImmutable();
        g2 = G1.newRandomElement().getImmutable();
        g = G1.newElement();
        System.out.println("g1=" + g1); 
        System.out.println("g2=" + g2);
        
        //将变量T1，T2V初始化为GT中的元素，GT是乘法群  
        GT = pairing.getGT();  
        gt1 = GT.newRandomElement().getImmutable();  
        gt2 = GT.newRandomElement().getImmutable();
        gt = GT.newElement();
        System.out.println("gt1=" + gt1); 
        System.out.println("gt2=" + gt2);
        System.out.println("计数常数 n = " + n);
    } 
       
	@Override
	public void Ad()
	{  
        System.out.println("-------------------Zr中加法运算Ad----------------------");   
        for(i=0;i<n;i++) z = z1.add(z2);
        System.out.println("z = z1 + z2 = " + z); 
    }

	@Override
	public void Ne()
	{
		System.out.println("-------------------Zr中加法逆元运算Ne----------------------");   
		for(i=0;i<n;i++) z = z1.negate(); 
        System.out.println("z = - z1 = " + z);
	}
	
	@Override
	public void Mu()
	{  
        System.out.println("-------------------Zr中乘法运算Mu----------------------");   
        for(i=0;i<n;i++) z = z1.mulZn(z2);
        System.out.println("z = z1 * z2 = " + z); 
    }
	
	@Override
	public void In()
	{
		System.out.println("-------------------Zr中乘法逆元运算In----------------------");   
		for(i=0;i<n;i++) z = z1.invert(); 
        System.out.println("z = z1' = " + z);
	}

	@Override
	public void Ex()
	{  
        System.out.println("-------------------Zr中指数运算Ex----------------------");   
        for(i=0;i<n;i++) z = z1.powZn(z2);
        System.out.println("z = z1 ^ z2 =" + z); 
    }
	
	@Override
	public void Add()
	{
		System.out.println("-------------------G1中加法运算Add----------------------");
		for(i=0;i<n;i++) g = g1.add(g2);
		System.out.println("g = g1 + g2 = " + g); 
	}
	
	@Override
	public void Neg()
	{
		System.out.println("-------------------G1中加法逆元运算Neg----------------------");   
		for(i=0;i<n;i++) g = g1.negate(); 
        System.out.println("g = - g1 = " + g);
	}
	
	@Override
	public void PM()
	{
		System.out.println("-------------------G1中点乘运算PM----------------------");   
		for(i=0;i<n;i++) g = g1.mul(g2); 
        System.out.println("g = g1 · g2 = " + g);
	}
	
	@Override
	public void Mul()
	{
		System.out.println("-------------------GT中乘法运算Mul----------------------");   
		for(i=0;i<n;i++) gt = gt1.mul(gt2); 
        System.out.println("gt = gt1 · gt2 = " + gt);
	}
	
	@Override
	public void Inv()
	{
		System.out.println("-------------------GT中逆元运算Inv----------------------");   
		for(i=0;i<n;i++) gt = gt1.invert(); 
        System.out.println("gt = gt1' = " + gt);
	}
	
	@Override
	public void Exp()
	{  
        System.out.println("-------------------GT中指数运算Exp----------------------");   
        for(i=0;i<n;i++) gt = gt1.powZn(gt2);
        System.out.println("gt = gt1 ^ gt2 = " + gt); 
    }
	
	@Override
	public void P()
	{  
        System.out.println("-------------------GT中配对运算P----------------------");   
        for(i=0;i<n;i++) gt = pairing.pairing(g1,g2);
        System.out.println("gt = g(gt1, gt2) = " + gt); 
    }
}