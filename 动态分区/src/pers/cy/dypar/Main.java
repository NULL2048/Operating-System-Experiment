package pers.cy.dypar;

import pers.cy.dypar.entity.Partition;
import pers.cy.dypar.factory.Factory;
import pers.cy.dypar.service.AllocatedMemoryService;
import pers.cy.dypar.service.IOService;
import pers.cy.dypar.service.RecoveryMemoryService;
import pers.cy.dypar.util.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main implements Constant {
    private static IOService ioService = Factory.getIOServiceInstance();
    private static AllocatedMemoryService allocatedMemoryService = Factory.getAllocatedMemoryServiceInstance();
    private static RecoveryMemoryService recoveryMemoryService = Factory.getRecoveryMemoryServiceInstance();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // 创建初始分区分配表
        List<Partition> partitionList = new ArrayList<>();
        partitionList.add(new Partition(0, 0, 1024, STATUS_WAIT));

        while (true) {
            System.out.println("\n1. 分配内存");
            System.out.println("2. 回收内存");
            System.out.println("3. 显示内存使用情况");
            System.out.println("4. 退出\n");

            System.out.print("\n请输入选择：");
            int select = scan.nextInt();

            switch (select) {
                case 1:
                    allocatedMemoryService.allocatedMemory(partitionList);
                    break;
                case 2:
                    recoveryMemoryService.recoveryMemory(partitionList);
                    break;
                case 3:
                    ioService.outputMemoryUsage(partitionList);
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("输入有误,请重新输入");
                    break;
            }
        }
    }
}
