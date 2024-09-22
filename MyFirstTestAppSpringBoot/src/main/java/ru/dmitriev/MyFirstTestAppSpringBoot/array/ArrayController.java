package ru.dmitriev.MyFirstTestAppSpringBoot.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArrayController {
    private ArrayList<String> arrayList;
    private Map<Integer, String> hashMap;
    private int mapKeyCounter = 0;
    @GetMapping("/array")
    public ArrayList<String> showArrayList() {
        return arrayList;
    }
    @GetMapping("/upd-array")
    public String updateArrayList(@RequestParam(required = true) String value) {
        if (arrayList == null) {
            arrayList = new ArrayList<String>();
            arrayList.add(value);
            return "Created Array List";
        }

        arrayList.add(value);
        return String.format("Add %s", value);
    }

    @GetMapping("/upd-map")
    public String updateHashMap(@RequestParam(required = true) String value) {
        if (hashMap == null) {
            hashMap = new HashMap<Integer, String>();
            hashMap.put(mapKeyCounter++, value);
            return "Created Hash Map";
        }

        hashMap.put(mapKeyCounter++, value);
        return String.format("Add %s", value);
    }

    @GetMapping("/map")
    public Map<Integer, String> showHashMap() {
        return hashMap;
    }

    @GetMapping("/all-length")
    public String showAllLength() {
        Integer arraySize = arrayList == null ? 0 : arrayList.size();
        Integer mapSize = hashMap == null ? 0 : hashMap.size();
        return "Array List size: " + arraySize + ", Hash Map size: " + mapSize;
    }
}
