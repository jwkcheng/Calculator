package net.jeremycheng.Calculator;

import java.util.HashMap;
import java.util.Map;

public class Memory
{

	private static Memory memoryInstance = null;

	private Map<String, Integer> memoryMap = new HashMap<>();

	public static Memory getInstance()
	{
		if (memoryInstance == null)
		{
			memoryInstance = new Memory();
		}
		return memoryInstance;
	}

	public void put(String key, Integer value)
	{
		memoryMap.put(key, value);
	}

	public Integer get(String key)
	{
		return memoryMap.get(key);
	}

	public void clear()
	{
		memoryMap.clear();
	}

	public boolean contains(String key)
	{
		return memoryMap.containsKey(key);
	}

	public void remove(String key)
	{
		memoryMap.remove(key);

	}
}
