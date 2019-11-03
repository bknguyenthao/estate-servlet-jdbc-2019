<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingURL" value="/admin-building?action = LIST"></c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách tòa nhà</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="<c:url value="/admin-home"/>">Trang chủ</a></li>
					<li><a href="<c:url value="/admin-building?action=LIST"/>">Danh
							sách sản phẩm</a></li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<form action="admin-building" method="get">
							<input type="hidden" name="action" value="LIST" />
							<!-- 					search box -->
							<div class="widget-box table-filter">
								<div class="widget-header">
									<h4 class="widget-title">Tìm kiếm</h4>
									<div class="widget-toolbar">
										<a href="#" data-action="collapse"> <i
											class="ace-icon fa fa-chevron-up"></i>
										</a>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<div class="form-horizontal">
											<div class="form-group">
												<div class="col-sm-12">
													<label><b>Tên Sản phẩm</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="name" value="${model.name}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Quận hiện có</b></label>
													<div class="fg-line">
														<select class="form-control" name="district">
															<option value="">-- Chọn quận --</option>
															<c:forEach var="item" items="${districts}">
																<option value="${item.key}">${item.value}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Phường</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="ward" value="${model.ward}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Đường</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="street" value="${model.street}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-3">
													<label><b>Diện tích sàn</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="buildingArea" value="${model.buildingArea}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Số tầng hầm</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="numberOfBasement" value="${model.numberOfBasement}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Hướng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="direction" value="${model.numberOfBasement}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Hạng</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="level" value="${model.level}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-3">
													<label><b>Diện tích từ</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="areaFrom" value="${model.areaFrom}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Diện tích đến</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="areaTo" value="${model.areaTo}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Giá thuê từ</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="costRentFrom" value="${model.costRentFrom}" />
													</div>
												</div>
												<div class="col-sm-3">
													<label><b>Giá thuê đến</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="costRentTo" value="${model.costRentTo}" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-4">
													<label><b>Tên quản lý</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="managerName" value="${model.managerName}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Điện thoại quản lý</b></label>
													<div class="fg-line">
														<input type="text" class="form-control input-sm"
															name="managerPhone" value="${model.managerPhone}" />
													</div>
												</div>
												<div class="col-sm-4">
													<label><b>Chọn nhân viên phụ trách</b></label>
													<div class="fg-line">
														<select class="form-control" id="select">
															<option value="">-- Chọn nhân viên phụ trách --</option>
															<option value="">Nhân viên A</option>
															<option value="">Nhân viên B</option>
															<option value="">Nhân viên C</option>
															<option value="">Nhân viên D</option>
														</select>
													</div>
												</div>
											</div>
											<div class="form-group">
												<div class="col-sm-6">
													<label><b>Chọn loại tòa nhà</b></label>
													<div class="fg-line">
														<c:forEach var="item" items="${buildingtypes}">
															<label class="checkbox-inline"><input
																type="checkbox" value="${item.key}"
																name="buildingTypes">${item.value}</label>
														</c:forEach>
													</div>
												</div>
											</div>

											<div class="form-group">
												<div class="col-sm-6">
													<button type="submit" class="btn btn-sm btn-success">
														Tìm kiếm <i
															class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
						<!-- 						button add, delete -->
						<div class="table-btn-controls">
							<div class="pull-right tableTools-container">
								<div class="dt-buttons btn-overlap btn-group">
									<a flag="info"
										class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Thêm tòa nhà'
										href='<c:url value="/admin-building?action=EDIT"/>'> <span><i
											class="fa fa-plus-circle bigger-110 purple"></i></span>
									</a>
									<button type="button"
										class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
										data-toggle="tooltip" title='Xóa tòa nhà'>
										<span><i class="fa fa-trash-o bigger-110 pink"></i></span>
									</button>
								</div>
							</div>
						</div>

					</div>
				</div>
				<!-- 				table -->
				<div class="row">
					<div class="col-xs-12">
						<table class="table">
							<thead>
								<tr>
									<th>Tên sản phẩm</th>
									<th>Địa chỉ</th>
									<th>Giá thuê</th>
									<th>Diện tích thuê</th>
									<th>Loại tòa nhà</th>
									<th>Tên quản lý</th>
									<th>SĐT quản lý</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${model.listResult}">
									<tr>
										<td>${item.name}</td>
										<td>${item.address}</td>
										<td>${item.costRent}</td>
										<td>${item.rentArea}</td>
										<td>${item.buildingType}</td>
										<td>${item.managerName}</td>
										<td>${item.managerPhone}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
</body>
</html>