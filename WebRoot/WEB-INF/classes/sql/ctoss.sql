select * from (select c.*,rownum r from cost c where rownum<?) where r>?;

(page-1)	*pagesize  max
page*pagesize +1    min

select * from wadmin_info ai inner join wadmin_role ar on ar.admin_id = ai.id
inner join wrole_info ri on ri.id = ai.id  inner join wrole_privilege rp on 
rp.role_id=ri.id where 1=1 and rp.privilege_id=1 and ri.id=2;
