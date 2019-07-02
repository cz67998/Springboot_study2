package com.example.demo.ThreadActor.racecondition;

/**
 * 宇宙能量守恒系统，遵循能量守恒定律，能量不会凭空消失，只会从一处转移到另一处
 * Created by IDEA
 * author:wangzhou
 * Data:2018/9/21
 * Time:10:21
 **/
public class EnergySystem {
    //能量盒子，能量储存的地方
    private final double[] energyBoxes;
    private final Object lock = new Object();

    /**
     * @param n             能量盒子的数量
     * @param initialEnergy 每个能量盒子初始含有的能量值
     */
    public EnergySystem(int n, double initialEnergy) {
        energyBoxes = new double[n];
        for (int i = 0; i < energyBoxes.length; i++) {
            energyBoxes[i] = initialEnergy;
        }
    }

    /**
     * 能量转移，从一个盒子到另一个盒子
     * lock可以用this代替
     *
     * @param from   能量源
     * @param to     能量终点
     * @param amount 能量值
     */
    public void transfer(int from, int to, double amount) throws InterruptedException {
        //既可以出现在方法体中也可以以下面的形式出现//互斥
        synchronized (lock) {

            // if (energyBoxes[from] < amount) return;
            while (energyBoxes[from] < amount) {
                //使线程进入等待的状态，避免整个线程持续的申请锁
                //条件不满足时，任务都会被阻挡，而不是继续竞争CPU资源
                //将当前线程放入Wait set
                System.out.println("wait");
                lock.wait();
                // this.wait();
                //若果不用 lock.notifyAll();唤醒，线程就会堵塞
            }
            System.out.println(Thread.currentThread().getName());
            energyBoxes[from] -= amount;
            System.out.printf("从%d转移%10.2f单位能量到%d", from, amount, to);
            energyBoxes[to] += amount;
            System.out.printf(" 能量总和：%10.2f%n", getTotalEnergies());
            //唤醒所有lock对象上等待的线程//同步
            // lock.wait();和 lock.notifyAll();是在同一个线程中执行的
            lock.notifyAll();
            //this.notifyAll();
        }

    }

    /**
     * 得到总能量
     *
     * @return
     */
    public double getTotalEnergies() {
        double sum = 0;
        for (double amount : energyBoxes)
            sum += amount;
        return sum;
    }

    /**
     * 返回能量盒子的长度
     *
     * @return
     */
    public int getBoxAmount() {
        return energyBoxes.length;
    }

    public static void main(String[] args) {
        EnergySystem energySystem = new EnergySystem(1, 2);
        System.out.println("1" + energySystem.energyBoxes[0]);
        energySystem.energyBoxes[0] = 4;
        System.out.println("2" + energySystem.energyBoxes[0]);
    }
}
