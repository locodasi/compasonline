INSERT INTO `usuario` (`id`, `contra`, `email`, `enabled`, `usuario`) VALUES ('1', '$2a$10$I/ZSTUbvPn1UuO540LL7hOlbI6qwVg88uDPdyerpbHe2E9PoDgeHm', 'admin@admin.com', b'1', 'admin');
INSERT INTO `usuario` (`id`, `contra`, `email`, `enabled`, `usuario`) VALUES ('2', '$2a$10$oxGUFaP5yTUiG7aUeWsQkecBWFeE5aoH3rdw1R5VOOjStKp2GwTiS', 'admin@admin.com', b'1', 'Lucas');
INSERT INTO `user_role` (`id`, `role`, `user_id`) VALUES ('1', 'ROLE_ADMIN', '1');
INSERT INTO `user_role` (`id`, `role`, `user_id`) VALUES ('2', 'ROLE_USER', '2');

INSERT INTO `rubro` (`descripcion`) VALUES ('Exteriores');
INSERT INTO `rubro` (`descripcion`) VALUES ('Interiores');
INSERT INTO `rubro` (`descripcion`) VALUES ('Accesorios');
