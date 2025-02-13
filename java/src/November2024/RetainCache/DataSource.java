package November2024.RetainCache;

public interface DataSource<K,V> {
    V getData(K key);
}
