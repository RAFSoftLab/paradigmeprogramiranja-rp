package raf.rs.primeri2;

import reactor.core.publisher.Flux;

public class PrimeriZip {

    public static void main(String[] args) {
        Flux<Integer> ints = Flux.range(1,4);
        Flux<String> strings = Flux.just("aa","bb","cc","dd");

        Flux.zip(ints,strings).subscribe(t-> System.out.println(t.getT1()+"-"+t.getT2()));


        Flux.zip(ints,strings).subscribe(t-> System.out.println(t.toList().size()));

        Flux.zip(ints,strings).subscribe(t-> System.out.println(t.toList().size()));

        Flux.zip(objs->objs[0], ints,strings).subscribe(o-> System.out.println(o));


        Flux<Integer> ints1 = Flux.range(1,4);
        Flux<Integer> ints2 = Flux.range(20,4);
        Flux<Integer> ints3 = Flux.range(100,5);


        Flux.zip(objs->(int)objs[0]+(int)objs[1]+(int)objs[2]+(int)objs[3],ints,ints1,ints2,ints3).subscribe(o-> System.out.println(o));

        ints1.zipWith(ints2,(x,y)->x-y).subscribe(System.out::println   );

    }
}
