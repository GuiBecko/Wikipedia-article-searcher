import java.util.LinkedHashMap;
import java.util.Map;

public class SummaryCache {
    private final int CAPACIDADE;
    private Map<String, String> cache;

    public SummaryCache(int Capacidade){
        this.CAPACIDADE = Capacidade;

        this.cache = new LinkedHashMap<>(Capacidade, 0.75f,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                // Remove automaticamente o mais antigo quando ultrapassar o limite
                return size() > CAPACIDADE;
            }
        };

    }

    public synchronized  void put(String url, String summary){
        cache.put(url, summary);
    }

    public synchronized String get(String url){
        return cache.get(url);
    }
}
