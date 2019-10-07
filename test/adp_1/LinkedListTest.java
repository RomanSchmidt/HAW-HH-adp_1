package adp_1;

class LinkedListTest extends ATestList {

    private LinkedList<String> _testList;

    @Override
    protected AMyList<String> getList() {
        return this._testList;
    }

    @Override
    protected void createList() {
        this._testList = new LinkedList<>();
    }
}