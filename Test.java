public @syntax.NoX class Test {
    int x = 10;

    private class Okay {
    }

    public void hello(int xy) {
        System.out.println(xy);
        System.out.println(xy);
        int xyz = 5;
        System.out.println(Integer.toString(xyz));
        int aa = xyz + 5;
        int xyz = 5;
        Okay okayx = new Okay();
    }
}
