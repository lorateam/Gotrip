package service;

import mapper.CategoryMapper;
import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService implements CategoryInt{

    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public List<Category> listTwoCategory(){
        return categoryMapper.selectTwo();
    }
    @Override
    public Category getCategory(int id){
        return categoryMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<Category> listAll(){
        return categoryMapper.selectAll();
    }
    @Override
    public void delete(int id){
        categoryMapper.deleteByPrimaryKey(id);
    }
    @Override
    public Integer insert(Category category){
        categoryMapper.insert(category);
        return categoryMapper.selectNew(category.getName());
    }
}
