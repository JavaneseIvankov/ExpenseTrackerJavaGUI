package org.example.facade;

import java.util.ArrayList;
import java.util.List;
import org.example.Context;
import org.example.models.CategoryModel;
import org.example.repositories.IAppRepository;

public class Category {
   private int id;
   private String name;

   // Ambil dari konteks global (DI)
   static private IAppRepository _repo = Context.appRepo;

   CategoryModel toModel() {
      return new CategoryModel(id, name);
   }

   static void setRepository(IAppRepository repo) {
      _repo = repo;
   }

   public Category() {}

   private Category(CategoryModel model) {
      this.id = model.id;
      this.name = model.name;
   }

   public static List<Category> getAll() {
      List<CategoryModel> models = _repo.getCategories();
      List<Category> res = new ArrayList<>();
      for (CategoryModel model : models) {
         res.add(new Category(model));
      }
      return res;
   }

   public Category(String name) {
      CategoryModel model = _repo.createCategory(name);
      if (model == null) {
         throw new IllegalStateException("Failed to create category: repository returned null");
      }
      this.name = model.name;
      this.id = model.id;
   }

   public static Category getById(int id) {
      CategoryModel model = _repo.getCategory(id);
      return new Category(model);
   }

   public static Category getByName(String name) {
      CategoryModel model = _repo.getCategory(name);
      return new Category(model);
   }

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public void setName(String newName) {
      CategoryModel updated = _repo.renameCategory(this.id, newName);
      if (updated == null) {
         throw new IllegalStateException("Failed to rename category: repository returned null");
      }
      this.name = updated.name;
   }
}
