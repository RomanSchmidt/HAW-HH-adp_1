package adp_1;

class MyListTest extends ATestList {

    private MyList<String> _testList;

    @Override
    protected MyList<String> getList() {
        return this._testList;
    }

    @Override
    protected void createList() {
        this._testList = new MyList<>();
    }
}