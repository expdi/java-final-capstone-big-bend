package trackService.repository;
import java.time.LocalDate;
import java.util.List;

public interface BaseDAO<T> {

    //1.Basic Create, Update, Delete functionality
    public T create(T object);
    public void update(T object);
    public void delete(int id);
    //2.Get a Track by id
    public T get(int id);
    public List<T> getAll();
    void clearDatabase();
    void initDatabase();

}
