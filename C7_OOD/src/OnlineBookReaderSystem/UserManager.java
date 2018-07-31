import java.util.*;
public class UserManager {
	Map<String, Reader> map;
	public UserManager() {
		this.map = new HashMap<>();
	}
	
	public Set<Reader> getAllReaders() {
		Set<Reader> res = new HashSet<>();
		for(String id : map.keySet()) res.add(map.get(id));
		return res;
	}
	
	public void setPreium(Reader r) {
		if(!r.isP()) r.setPre(true);
	}
	
	public void deactivatePreium(Reader r) {
		if(r.isP()) r.setPre(false);
	}

	public void addReader(Reader r) {
		map.put(r.getId(), r);
	}
	
	public boolean removeReader(Reader r) {
		if(!map.containsKey(r)) return false;
		map.remove(r.getId());
		return true;
	}
	
	public Reader getReader(String id) {
		return map.get(id);
	}
	
	public int getNumOfReaders() {
		return map.size();
	}
}
