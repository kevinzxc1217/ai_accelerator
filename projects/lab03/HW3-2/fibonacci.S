## fibonacci.S

    .text                          # code section 
    .global fibonacci_asm          # declar the sum_asm function as a  global function
    .type fibonacci_asm, @function # define sum_asm as a function 
fibonacci_asm:

prologue:
    addi   sp, sp, -4        # �i�� stack pointer ������
    sw     ra, 0(sp)          # �x�s return address
    li     a1,0
    
funct_start:
    jal    formula               # �}�l�i�� factorial �B��
    
epilogue:
    mv a0,a1
    lw     ra, 0(sp)          # �x�s return address
    addi   sp, sp, 4        # �i�� stack pointer ������
    jr     ra

ret_one:
    li     t2, 1
    lw     ra, 0(sp)         #���Xra�a�}
    addi   sp, sp, 8 
    add    a1, t2, a1 		
    jr     ra
    
ret_zero:
    li     t3, 0
    lw     ra, 0(sp)         #���X��A�Nblock�M��
    addi   sp, sp, 8 
    add    a1, t3, a1 		
    jr     ra 


formula:
    addi   sp, sp, -8        # �i�� stack pointer ������
    sw     ra, 0(sp)          # �x�s return address
    sw     a0, 4(sp)          # �x�s temp data
	
    
    li     t0, 1              
    li     t1, 2             
    blt    a0, t0, ret_zero   # if(n==0)return 0
    blt    a0, t1, ret_one    # if(n==1)return 1       		 

    addi   a0, a0, -1         # f(n-1)
    jal    formula       		  
    
    lw     a0, 4(sp)          # �v�@���X
    addi   a0, a0, -2         # f(n-2)
    jal    formula       		  

    lw     ra, 0(sp)         #���X��A�Nblock�M��
    addi   sp, sp, 8 
    jr     ra     
  

    
    .size fibonacci_asm, .-fibonacci_asm