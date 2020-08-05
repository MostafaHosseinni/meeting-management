package ir.mine.project.base.authenticate.sec.authenticate.user.server.user;//package vezarat.behdasht.yas.base.authenticate.sec.authenticate.user.server.user;
//
//import net.ictcert.framework.corenew.crud.dao.BaseCrudDaoImpl;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.hibernate.transform.Transformers;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class BaseUserDAO extends BaseCrudDaoImpl<BaseUserEntity, Long>
//		implements IBaseUserDAO {
//
//	@Override
//	public BaseUserEntity getUserByUserName(String userName) {
//
//		DetachedCriteria detCriteria = createCriteria();
//		if (userName != null && !"".equals(userName)) {
//			detCriteria.add(Restrictions.eq("username", userName));
//		}
//		Criteria criteria = getCriteriaFromDetachedCriteria(detCriteria);
//		criteria.setMaxResults(1);
//
//		criteria.setProjection(Projections.projectionList()
//				.add(Projections.property("id"), "id")
//				.add(Projections.property("username"), "username")
//				.add(Projections.property("password"), "password")
//				.add(Projections.property("expireDate"), "expireDate"));
//
//		criteria.setResultTransformer(Transformers
//				.aliasToBean(BaseUserEntity.class));
//
//		BaseUserEntity baseUserEntity = getEntityFromCriteria(criteria);
//		if (baseUserEntity != null)
//			return baseUserEntity;
//		else
//			return null;
//
//	}
//
//}
