package com.nupal.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.nupal.exception.ArticleException;
import com.nupal.pojo.Article;
import com.nupal.pojo.Customer;

public class ArticleDAO extends DAO{
	
		public ArticleDAO() {
		}

		 
		public List<Article> get(String key,String flag) throws ArticleException {
			try {
				begin();
				String q="";
				if (flag.equalsIgnoreCase("content")) {
	                q = "from Article where content= :fname";
	            } else if (flag.equalsIgnoreCase("author")) {
	                q = "from Article where author= :fname";
	            } 
	            /*else if (flag.equalsIgnoreCase("email")) {
	                q = "from User where email= :fname";
	            }*/
				Query q1 = getSession().createQuery(q);
				q1.setString("fname", key);		
				List<Article> articles =q1.list();
				commit();
				return articles;
			} catch (HibernateException e) {
				rollback();
				throw new ArticleException("Could not get articles with " + key, e);
			}
		}

		public Article register(Article a,long cid)
				throws ArticleException {
			try {
				begin();
				String query = "from Customer where personID= :personID";
				Query q1 = getSession().createQuery(query);
				q1.setLong("personID", cid);
				Customer person =(Customer) q1.uniqueResult();
				Set<Article> articles = person.getArticleList();
				
				articles.add(a);
				person.setArticleList(articles);
				
				//getSession().save(a);
				commit();
				return a;

			} catch (HibernateException e) {
				rollback();
				throw new ArticleException("Exception while creating article: " + e.getMessage());
			}
		}

		public void delete(Article article) throws ArticleException {
			try {
				begin();
				getSession().delete(article);
				commit();
			} catch (HibernateException e) {
				rollback();
				throw new ArticleException("Could not delete article " + article.getArticleID(), e);
			}
		}

}
