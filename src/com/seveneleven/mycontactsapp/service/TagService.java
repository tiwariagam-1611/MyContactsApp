package com.seveneleven.mycontactsapp.service;

import com.seveneleven.mycontactsapp.model.Tag;
import java.util.HashSet;
import java.util.Set;

public class TagService {
    private static final Set<Tag> tagPool = new HashSet<>();

    public Tag createTag(String name) {
        Tag newTag = Tag.of(name);
        if (tagPool.contains(newTag)) {
            return tagPool.stream().filter(t -> t.equals(newTag)).findFirst().get();
        } else {
            tagPool.add(newTag);
            System.out.println("Created new tag: " + newTag);
            return newTag;
        }
    }

    public void listTags() {
        System.out.println("--- All Tags ---");
        tagPool.forEach(System.out::println);
    }
}
