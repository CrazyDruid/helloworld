package SmallTest;

import com.google.common.base.Optional;

/**
 * Created by Administrator on 2018/5/30.
 */
public class GuavaTest1 {

    public static void main(String[] args) {
        GuavaTest1 gt = new GuavaTest1();
        Integer i1 = null;
        Integer i2 = new Integer(10);
        Optional<Integer> io1 = Optional.fromNullable(i1);
        Optional<Integer> io2 = Optional.of(i2);
        System.out.println(gt.sum(io1, io2));
    }

    public Integer sum(Optional<Integer> io1, Optional<Integer> io2) {
        System.out.println(io1.isPresent());
        System.out.println(io2.isPresent());
        Integer i1 = io1.or(new Integer(1));
        Integer i2 = io2.get();
        return i1 + i2;
    }
}
